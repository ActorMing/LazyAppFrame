package com.lazy.lazydevelopeframe.base.rxjava;

import com.lazy.lazydevelopeframe.base.exception.LazyException;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/9.
 *  desc   : RxJava线程调度类
 *  modify :
 * </pre>
 */

public class RxSchedulerManager {

    private static FlowableTransformer flowableTransformer;

    /**
     * rxAndroid 线程切换 (订阅和取消订阅在子线程中,消费在主线程)
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer io_main() {
        return new FlowableTransformer() {
            @Override
            public Publisher apply(Flowable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 处理异常的
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer handleErrTransformer() {
        if (flowableTransformer != null) return flowableTransformer;
        else return flowableTransformer = new FlowableTransformer() {
            @Override
            public Publisher apply(Flowable upstream) {
                return upstream.onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }


    /**
     * http 响应函数处理
     *
     * @param <T>
     */
    private static class HttpResponseFunc<T> implements Function<java.lang.Throwable, Flowable<T>> {
        @Override
        public Flowable<T> apply(Throwable throwable) throws Exception {
            return Flowable.error(LazyException.handleException(throwable));
        }
    }
}
