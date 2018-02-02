package com.lazy.lazydevelopeframe.base.api;

import com.lazy.lazydevelopeframe.base.bean.BaseResponseBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/2/1.
 *  desc   : baseApiService
 *  modify : 基本的 api service
 * </pre>
 */

public interface BaseApiService {

    @GET()
    <T> Flowable<BaseResponseBean> get(@Url String url, @QueryMap HashMap<String, Object> maps);

    @GET()
    <T> Flowable<BaseResponseBean> getEncoded(@Url String url, @QueryMap(encoded = true) HashMap<String, Object> maps);

    @FormUrlEncoded
    @POST("{url}")
    <T> Flowable<BaseResponseBean> postForm(@Url String url, @FieldMap HashMap<String, Object> maps);

    @FormUrlEncoded
    @POST("{url}")
    <T> Flowable<BaseResponseBean> postFormEncoded(@Url String url, @FieldMap(encoded = true) HashMap<String, Object> maps);

    @DELETE()
    <T> Flowable<BaseResponseBean> delete(@Url String url, @QueryMap HashMap<String, Object> maps);

    @DELETE()
    <T> Flowable<BaseResponseBean> deleteEncoded(@Url String url, @QueryMap(encoded = true) HashMap<String, Object> maps);

    @PUT()
    <T> Flowable<BaseResponseBean> put(@Url String url, @FieldMap HashMap<String, Object> maps);

    @PUT()
    <T> Flowable<BaseResponseBean> putEncoded(@Url String url, @FieldMap(encoded = true) HashMap<String, Object> maps);

}
