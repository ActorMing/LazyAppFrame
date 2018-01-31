package com.lazy.lazydevelopeframe.base.di.component;

import android.app.Application;

import com.lazy.lazydevelopeframe.base.di.module.base.ApiBaseServerModule;
import com.lazy.lazydevelopeframe.base.di.module.base.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/30.
 *  desc   : application 桥梁接口
 *  modify :
 * </pre>
 */

@Singleton
@Component(modules = {AppModule.class, ApiBaseServerModule.class})
public interface AppComponent {

    void inject(Application application);

    Retrofit getRetrofit();
}
