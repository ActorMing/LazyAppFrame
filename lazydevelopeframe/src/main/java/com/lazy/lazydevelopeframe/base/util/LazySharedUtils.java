package com.lazy.lazydevelopeframe.base.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lazy.lazydevelopeframe.base.config.LazyConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/3/7.
 *  desc   : SharedPreferences 工具类 <所有的数据用 string 类型进行保存>
 *  modify :
 * </pre>
 */

public class LazySharedUtils {

    private static LazySharedUtils instance;

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    private LazySharedUtils() {
    }

    public static LazySharedUtils getInstance() {
        if (instance == null) {
            synchronized (LazySharedUtils.class) {
                if (instance == null) {
                    instance = new LazySharedUtils();
                }
            }
        }

        instance.init();

        return instance;
    }

    /**
     * 初始化相关的对象
     */
    private void init() {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(LazyConfig.get().getContext());
        }
        if (editor == null) editor = preferences.edit();
    }

    /**
     * 保存数据到 SharedPreferences 中
     *
     * @param hashMap 需要保存的键值对map
     */
    public void saveData(@NonNull HashMap<String, String> hashMap) {
        if (hashMap.isEmpty()) return;
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (TextUtils.isEmpty(entry.getKey())) continue;
            editor.putString(entry.getKey(), entry.getValue());
        }
        editor.commit();
    }

    /**
     * 根据 keys 获取相对应的 values
     *
     * @param keys 数据的key
     * @return 返回相对应的key和value map对象
     */
    public HashMap<String, String> getData(String... keys) {
        HashMap<String, String> resultMap = new HashMap<>();
        if (keys == null || keys.length == 0) {
            return resultMap;
        }
        for (String key : keys) {
            if (TextUtils.isEmpty(key)) continue;
            resultMap.put(key, preferences.getString(key, ""));
            if (resultMap.size() == keys.length) break;
        }
        return resultMap;
    }


    /**
     * 根据 key 获取 value
     *
     * @param key 单个key
     * @return 返回相对应的value
     */
    public String getData(@NonNull String key) {
        return TextUtils.isEmpty(key) ? "" : preferences.getString(key, "");
    }

    /**
     * 移除某一个key 和相对的 value
     *
     * @param key
     */
    public void remove(@NonNull String key) {
        if (!TextUtils.isEmpty(key)) {
            editor.remove(key).commit();
        }
    }

    /**
     * 清空数据
     */
    public void clear() {
        editor.clear().commit();
    }
}
