package com.lazy.lazydevelopeframe.base.exception;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   :
 *  modify :
 * </pre>
 */

public class LazyThrowable extends Exception {

    private int code;
    private String message;

    public LazyThrowable(java.lang.Throwable throwable) {
        super(throwable);
    }

    public LazyThrowable(java.lang.Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public LazyThrowable(java.lang.Throwable throwable, int code, String message) {
        super(throwable);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
