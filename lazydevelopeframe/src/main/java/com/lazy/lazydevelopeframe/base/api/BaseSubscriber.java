package com.lazy.lazydevelopeframe.base.api;

import android.content.Context;

import com.lazy.lazydevelopeframe.base.bean.BaseResponseBean;
import com.lazy.lazydevelopeframe.base.exception.LazyException;
import com.lazy.lazydevelopeframe.base.exception.LazyThrowable;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   :
 *  modify :
 * </pre>
 */

public abstract class BaseSubscriber<T> extends DisposableSubscriber<T> {

    protected Context mContext;

    public BaseSubscriber() {
    }


    @Override
    final public void onError(java.lang.Throwable t) {
        if (t instanceof LazyThrowable) {
            onError((LazyThrowable) t);
        } else {
            onError(LazyException.handleException(t));
        }
        onComplete();
    }

    @Override
    final public void onNext(T t) {
        try {
            onNext(((BaseResponseBean) t).getCode(), ((BaseResponseBean) t).getMsg(), t);
        } catch (Exception e) {
            onNext(1, String.valueOf(t), t);
        }
        onComplete();
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(LazyThrowable throwable);

    public abstract void onNext(int code, String msg, T response);
}
