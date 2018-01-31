package com.lazy.lazyappframe.app.di.component;

import com.lazy.lazyappframe.app.MainActivity;
import com.lazy.lazyappframe.app.di.anniation.ScopeActivity;
import com.lazy.lazyappframe.app.di.module.MainModule;
import com.lazy.lazydevelopeframe.base.di.component.AppComponent;
import com.lazy.lazydevelopeframe.base.di.module.base.AppModule;

import dagger.Component;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/31.
 *  desc   :
 *  modify :
 * </pre>
 */

@ScopeActivity
@Component(dependencies = {AppComponent.class}, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity view);
}
