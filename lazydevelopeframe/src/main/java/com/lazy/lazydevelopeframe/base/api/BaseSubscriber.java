package com.lazy.lazydevelopeframe.base.api;

import android.content.Context;

import com.lazy.lazydevelopeframe.base.bean.BaseResponseBean;
import com.lazy.lazydevelopeframe.base.exception.LazyException;
import com.lazy.lazydevelopeframe.base.exception.LazyThrowable;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   :
 *  modify :
 * </pre>
 */

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    protected Context mContext;

    public BaseSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    public BaseSubscriber() {
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(s.hashCode());
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
        onNext(((BaseResponseBean) t).getCode(), ((BaseResponseBean) t).getMsg(), t);
        onComplete();
    }

    @Override
    public void onComplete() {

    }

    public abstract void onError(LazyThrowable throwable);

    public abstract void onNext(int code, String msg, T response);
}
