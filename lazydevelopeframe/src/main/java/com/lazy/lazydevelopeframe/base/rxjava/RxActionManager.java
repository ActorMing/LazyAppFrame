package com.lazy.lazydevelopeframe.base.rxjava;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/9.
 *  desc   : RxJava活动管理类
 *  modify :
 * </pre>
 */

public interface RxActionManager<T> {

    /**
     * 添加一个活动
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除某一个活动
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 移除所有的活动
     */
    void removeAll();

    /**
     * 取消某一个活动
     *
     * @param tag
     */
    void cancel(T tag);

    /**
     * 取消所有
     */
    void cancelAll();


}
