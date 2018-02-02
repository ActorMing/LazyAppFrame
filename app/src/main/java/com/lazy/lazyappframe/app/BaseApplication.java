package com.lazy.lazyappframe.app;

import android.app.Application;

import com.lazy.lazydevelopeframe.base.config.LazyConfig;
import com.lazy.lazydevelopeframe.base.di.component.AppComponent;
import com.lazy.lazydevelopeframe.base.di.component.DaggerAppComponent;
import com.lazy.lazydevelopeframe.base.di.module.base.AppModule;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/31.
 *  desc   :
 *  modify :
 * </pre>
 */

public class BaseApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        LazyConfig.init()
                .init(this)
                .setBaseHttpUrl("http://www.wanandroid.com/tools/mockapi/") // 必须要进行设置的 baseUrl
                .setCacheFileName("cache_fileName.txt") // 缓存文件名称 (默认:okHttpCacheFile)
                .setCacheFileSize(Long.valueOf(10 * 1024 * 1024)) // 缓存文件大小  (默认:10MB)
                .setConnTimeout(15) // 连接超时时间  (默认:15s)
                .setReadTimeout(15) // 读取超时时间  (默认:15s)
                .setWriteTimeout(15) // 写入超时时间  (默认:15s)
                .setHttpRetry(false) // 重试  (默认:false)
                .setResponseOk(200) // 返回正确的状态码  (默认:200)
                /**
                 * 接收所有主机名的地址  (默认:true)
                 *  注: 如果选择了 false 则需要填写自己所支持的hostname否则还是接收所有的主机名地址
                 *  .setReceiveAllHostname(false)
                 *  .setSupportHostnameArray("https://www.baidu.com", "https://www.google.cn");
                 */
                .setReceiveAllHostname(true) // 这里选择接收所有的 hostname 则 supportHostname 不会启用
                .setSupportHostnameArray("https://www.baidu.com", "https://www.google.cn") // 支持的 hostnameArray (选择了之后只会支持该array中的hostname)
                /**
                 * 忽略所有的 ssl 证书(注:信任所有的https)  (默认:true)
                 *  注: 如果选择了 false 则需要配置自己的ssl证书
                 *  .setIgnoreSSL(false)
                 *  .setCertificates(R.rwa.scera)
                 */
                .setIgnoreSSL(true) // 选择了忽略 ssl 证书则 setCertificate  不启用
//                .setCertificates(R.raw.scera) // 存放在 raw 文件夹下的证书文件
                .setSSLProtocolType("TLS") // ssl 协议类型  (默认:TLS)
                .setCertificateType("X.509"); // 证书类型 (默认:X.509)


        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
            appComponent.inject(this);
        }


    }
}
