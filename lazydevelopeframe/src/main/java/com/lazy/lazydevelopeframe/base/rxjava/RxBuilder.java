package com.lazy.lazydevelopeframe.base.rxjava;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/22.
 *  desc   : 处理
 *  modify :
 * </pre>
 */

public class RxBuilder {

    private static Context mContext;

    public static void init(@NonNull Application application) {
        RxBuilder.mContext = application.getApplicationContext();
    }

    public static Context getContext() {
        if (mContext == null)
            throw new RuntimeException("please call RxBuilder.init method in Application.onCreate method");
        return mContext;
    }
}
