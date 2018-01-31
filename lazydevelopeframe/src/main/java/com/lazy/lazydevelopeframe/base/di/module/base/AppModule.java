package com.lazy.lazydevelopeframe.base.di.module.base;

import android.app.Application;
import android.content.Context;

import com.lazy.lazydevelopeframe.base.di.annotation.ScopeApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/22.
 *  desc   :
 *  modify :
 * </pre>
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Application application) {
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    Context providerContext() {
        return context;
    }
}
