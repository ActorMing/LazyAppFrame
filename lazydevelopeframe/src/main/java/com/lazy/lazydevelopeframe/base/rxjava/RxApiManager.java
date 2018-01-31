package com.lazy.lazydevelopeframe.base.rxjava;

import android.util.ArrayMap;

import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/9.
 *  desc   : api调用管理类
 *  modify :
 * </pre>
 */

public class RxApiManager implements RxActionManager<Object> {

    private static RxApiManager sInstance = null;
    private ArrayMap<Object, Disposable> arrayMap;

    private RxApiManager() {
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
        }
    }

    public static RxApiManager get() {
        if (sInstance == null) {
            synchronized (RxApiManager.class) {
                if (sInstance == null) {
                    sInstance = new RxApiManager();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void add(Object tag, Disposable disposable) {
        if (tag != null && disposable != null) {
            arrayMap.put(tag, disposable);
        }
    }

    @Override
    public void remove(Object tag) {
        if (!arrayMap.isEmpty() && tag != null) {
            arrayMap.remove(tag);
        }
    }

    @Override
    public void removeAll() {
        if (!arrayMap.isEmpty()) {
            arrayMap.clear();
        }
    }

    @Override
    public void cancel(Object tag) {
        if (arrayMap.isEmpty() || tag == null) {
            return;
        }

        // 没有处理的进行处理
        if (arrayMap.get(tag) != null && !arrayMap.get(tag).isDisposed()) {
            arrayMap.get(tag).dispose();
            arrayMap.remove(tag);
        }
    }

    @Override
    public void cancelAll() {
        if (!arrayMap.isEmpty()) {
            Set<Object> objects = arrayMap.keySet();
            for (Object object : objects) {
                cancel(object);
            }
        }
    }
}
