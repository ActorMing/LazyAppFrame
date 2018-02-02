package com.lazy.lazydevelopeframe.base.bean;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/23.
 *  desc   : 数据返回基类
 *  modify :
 * </pre>
 */

public class BaseResponseBean {

    private int code;
    private long sys_time;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getSys_time() {
        return sys_time;
    }

    public void setSys_time(long sys_time) {
        this.sys_time = sys_time;
    }

    @Override
    public String toString() {
        return "BaseResponseBean{" +
                "code=" + code +
                ", sys_time=" + sys_time +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
