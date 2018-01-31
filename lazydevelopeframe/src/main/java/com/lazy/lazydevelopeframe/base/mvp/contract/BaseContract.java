package com.lazy.lazydevelopeframe.base.mvp.contract;

import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/9.
 *  desc   : MVP契约接口的基类
 *  modify :
 * </pre>
 */

public interface BaseContract {

    /**
     * view接口基类
     */
    interface BaseContractView {

        /**
         * 初始化
         */
        void initialize();

        /**
         * 显示加载框
         */
        void showProgressDialog();

        /**
         * 关闭加载框
         */
        void dismissProgressDialog();
    }

    /**
     * model接口基类
     */
    interface BaseContractModel {

    }

    /**
     * presenter接口基类
     */
    interface BaseContractPresenter {

        /**
         * 分离有关联的对象
         */
        void detach();

        /**
         * 添加到请求队列
         *
         * @param tag
         * @param disposable
         */
        void addRequestQueue(Object tag, Disposable disposable);

        /**
         * 取消请求队列
         */
        void cancelRequestQueue();


    }
}
