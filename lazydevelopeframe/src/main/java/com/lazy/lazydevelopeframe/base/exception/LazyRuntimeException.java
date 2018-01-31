package com.lazy.lazydevelopeframe.base.exception;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   : 运行时异常
 *  modify :
 * </pre>
 */

public class LazyRuntimeException extends RuntimeException{

    public int code;
    public String message;

    public LazyRuntimeException(int code, String mess) {
        super(mess);
        this.code = code;
        this.message = mess;
    }
}
