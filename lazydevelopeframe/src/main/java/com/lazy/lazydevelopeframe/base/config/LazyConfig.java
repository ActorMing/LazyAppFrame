package com.lazy.lazydevelopeframe.base.config;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   :
 *  modify :
 * </pre>
 */

public class LazyConfig {

    private Context context;
    private String baseHttpUrl;
    private String cacheFileName = "okHttpCacheFile";
    private long cacheFileSize = 10 * 1024 * 1024;
    private long connTimeout = 15;
    private long readTimeout = 15;
    private long writeTimeout = 15;
    private boolean httpRetry = false;
    private int responseOk = 200;

    private LazyConfig() {
    }

    private static LazyConfig lazyConfig;

    public static LazyConfig init() {
        if (lazyConfig == null) {
            synchronized (LazyConfig.class) {
                if (lazyConfig == null) {
                    lazyConfig = new LazyConfig();
                }
            }
        }

        LazyExceptionTipConfig.init();

        return lazyConfig;
    }

    public static LazyConfig get() {
        if (lazyConfig == null) throw new NullPointerException("please call first init() method");
        return lazyConfig;
    }

    public LazyConfig init(Context context) {
        this.context = context.getApplicationContext();
        return this;
    }

    public LazyConfig setResponseOk(int responseOk) {
        this.responseOk = responseOk;
        return this;
    }

    public LazyConfig setBaseHttpUrl(@NonNull String baseHttpUrl) {
        this.baseHttpUrl = baseHttpUrl;
        return this;
    }

    public LazyConfig setCacheFileName(@NonNull String cacheFileName) {
        this.cacheFileName = cacheFileName;
        return this;
    }

    public LazyConfig setCacheFileSize(long cacheFileSize) {
        this.cacheFileSize = cacheFileSize;
        return this;
    }

    public LazyConfig setConnTimeout(long connTimeout) {
        this.connTimeout = connTimeout;
        return this;
    }

    public LazyConfig setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public LazyConfig setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public LazyConfig setHttpRetry(boolean httpRetry) {
        this.httpRetry = httpRetry;
        return this;
    }

    public boolean isHttpRetry() {
        return httpRetry;
    }

    public String getCacheFileName() {
        return cacheFileName;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public long getCacheFileSize() {
        return cacheFileSize;
    }

    public long getConnTimeout() {
        return connTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public String getBaseHttpUrl() {
        return baseHttpUrl;
    }

    public int getResponseOk() {
        return responseOk;
    }

    public Context getContext() {
        if (context != null) {
            return context;
        } else {
            throw new NullPointerException("请先调用init()方法");
        }
    }

    @Override
    public String toString() {
        return "LazyConfig{" +
                "baseHttpUrl='" + baseHttpUrl + '\'' +
                ", cacheFileName='" + cacheFileName + '\'' +
                ", cacheFileSize=" + cacheFileSize +
                ", connTimeout=" + connTimeout +
                ", readTimeout=" + readTimeout +
                ", writeTimeout=" + writeTimeout +
                ", httpRetry=" + httpRetry +
                ", responseOk=" + responseOk +
                '}';
    }
}
