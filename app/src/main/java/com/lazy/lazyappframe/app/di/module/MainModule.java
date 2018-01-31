package com.lazy.lazyappframe.app.di.module;

import com.lazy.lazyappframe.app.MainActivity;

import dagger.Module;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/31.
 *  desc   :
 *  modify :
 * </pre>
 */

@Module
public class MainModule {

    private MainActivity view;

    public MainModule(MainActivity view) {
        this.view = view;
    }


}
