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

        LazyConfig.init().init(this)
                .setBaseHttpUrl("http://www.wanandroid.com/tools/mockapi/");

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
            appComponent.inject(this);
        }


    }
}
