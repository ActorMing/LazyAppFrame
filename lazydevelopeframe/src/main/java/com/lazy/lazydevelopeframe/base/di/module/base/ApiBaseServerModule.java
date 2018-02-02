package com.lazy.lazydevelopeframe.base.di.module.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lazy.lazydevelopeframe.base.api.LazyHttpsFactory;
import com.lazy.lazydevelopeframe.base.config.LazyConfig;
import com.lazy.lazydevelopeframe.base.di.annotation.NetWork;
import com.lazy.lazydevelopeframe.base.di.annotation.NoNetWork;
import com.lazy.lazydevelopeframe.base.di.annotation.ScopeApp;
import com.vondear.rxtools.RxNetTool;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/22.
 *  desc   :
 *  modify :
 * </pre>
 */

@Module
public class ApiBaseServerModule {

    /**
     * 为下面的缓存对象提供缓存文件
     *
     * @param context AppModule提供过来的全局的Context
     * @return 缓存文件
     */
    @Provides
    @Singleton
    File providerCacheFile(Context context) {
        return new File(context.getCacheDir(), LazyConfig.get().getCacheFileName());
    }

    /**
     * 为下面的OkHttpClient提供缓存对象
     *
     * @param cacheFile AppModule提供过来的全局的Context
     * @return 缓存对象
     */
    @Provides
    @Singleton
    Cache providerCache(File cacheFile) {
        return new Cache(cacheFile, LazyConfig.get().getCacheFileSize());
    }

    /**
     * 在有网络的情况下，先去读缓存，设置的缓存时间到了，在去网络获取
     *
     * @return 网络拦截器
     */
    @NetWork
    @Provides
    @Singleton
    Interceptor providerNetWorkInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                /**
                 *  1. 获取 Request 对象
                 *  2. 获取 Chain 对象  Chain继续发送请求
                 *  3. 判断网络是否可用
                 *      3.1 网络可用设置get方式的缓存
                 */
                Request request = chain.request();

                Response response = chain.proceed(request);

                // 网络状态大于Disable说明可以使用,需要进入缓存
                if (!RxNetTool.isNetworkAvailable(context)) {
                    int maxAge = 60; // // 缓存失效时间,单位为秒
                    return response.newBuilder()
                            .removeHeader("Pragma")// 清除头信息(Pragma)，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                }
                return response;
            }
        };
    }

    /**
     * 在没有网络的情况下，取读缓存数据
     *
     * @return 无网络的拦截器
     */
    @NoNetWork
    @Provides
    @Singleton
    Interceptor providerNoNetWorkInterceptor(@ScopeApp final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                /**
                 *  1. 获取Request对象
                 *  2. 获取Response对象
                 *  3. 没有网络直接去缓存中获取数据
                 */
                Request request = chain.request();


                Response response = chain.proceed(request);

                // 没有网络取缓存数据
                if (!RxNetTool.isNetworkAvailable(context)) {
                    int maxStale = 60; // // 缓存失效时间,单位为秒
                    return response.newBuilder()
                            .removeHeader("Pragma")// 清除头信息(Pragma)，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .addHeader("Cache-Control", "public, only-if-cache, max-stale=" + maxStale)
                            .build();
                }
                return response;
            }
        };
    }


    // 信任所有的 SSL 签名
    @Provides
    @Singleton
    SSLSocketFactory providerSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providerInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("RetrofitBodyInterceptor", message);
            }
        });
        return loggingInterceptor.setLevel(level);
    }

    /**
     * 为下面的Retrofit提供OkHttpClient对象
     *
     * @param cache                上面提供的缓存对象
     * @param networkInterceptor   上面提供的网络拦截器
     * @param noNetWorkInterceptor 上面提供的无网络拦截器
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient(Cache cache,
                                      @ScopeApp Context context,
                                      @NetWork Interceptor networkInterceptor,
                                      @NoNetWork Interceptor noNetWorkInterceptor,
                                      SSLSocketFactory sslSocketFactory,
                                      HttpLoggingInterceptor httpLoggingInterceptor) {
        /**
         * 1. 设置连接超时时间
         * 2. 设置读取超时时间
         * 3. 设置写入超时时间
         * 4. 设置网络缓存拦截器
         * 5. 设置无网络的拦截器
         * 6. 设置缓存文件
         * 7. 设置失败后是否从新连接
         */

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(LazyConfig.get().getConnTimeout(), TimeUnit.SECONDS)
                .readTimeout(LazyConfig.get().getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(LazyConfig.get().getWriteTimeout(), TimeUnit.SECONDS)
                .addNetworkInterceptor(networkInterceptor)
                .addNetworkInterceptor(noNetWorkInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .retryOnConnectionFailure(LazyConfig.get().isHttpRetry());

        if (LazyConfig.get().isIgnoreSSL()) {
            builder.sslSocketFactory(sslSocketFactory);
        } else {
            if (LazyConfig.get().getCertificates() != null && LazyConfig.get().getCertificates().length > 0) {
                builder.sslSocketFactory(LazyHttpsFactory.getSSLSocketFactory(context, LazyConfig.get().getCertificates()));
            } else {
                builder.sslSocketFactory(sslSocketFactory);
            }
        }

        if (LazyConfig.get().isReceiveAllHostname()) { // receive all hostname address
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } else {
            if (LazyConfig.get().getSupportHostnameArray() != null
                    && LazyConfig.get().getSupportHostnameArray().length > 0) { // only receive support specified  hostname address
                builder.hostnameVerifier(LazyHttpsFactory.getHostnameVerifier(LazyConfig.get().getSupportHostnameArray()));
            } else {
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            }
        }

        return builder.build();
    }

    /**
     * 提供一个全局唯一的Retrofit对象,方便外部进行网络数据访问
     *
     * @param okHttpClient 上面提供的OkHttpClient对象
     * @return Retrofit
     */
    @Provides
    @Singleton
    Retrofit providerRetrofit(OkHttpClient okHttpClient) {

        /**
         *  . 设置OkHttpClient
         *  . 设置回调适配工厂对象RxJavaAdapterFactory
         *  . 设置数据转换工厂对象GsonConverterFactory 或者使用 FastJsonConverterFactory
         *  . 设置字符串转换工厂对象
         */
        okHttpClient.sslSocketFactory();
        Retrofit.Builder builder = new Retrofit.Builder();
        if (TextUtils.isEmpty(LazyConfig.get().getBaseHttpUrl())) {
            throw new NullPointerException("baseUrl can not null");
        }
        builder.baseUrl(LazyConfig.get().getBaseHttpUrl());
        builder.client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create());
        return builder.build();
    }
}
