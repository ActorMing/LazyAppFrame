package com.lazy.lazydevelopeframe.base.exception;

import android.net.ParseException;
import android.os.NetworkOnMainThreadException;
import android.text.TextUtils;

import com.lazy.lazydevelopeframe.base.config.LazyConfig;
import com.lazy.lazydevelopeframe.base.config.LazyExceptionTipConfig;
import com.vondear.rxtools.RxNetTool;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLPeerUnverifiedException;

import retrofit2.HttpException;

import static com.lazy.lazydevelopeframe.base.exception.LazyException.ERROR.UNKNOWN;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/29.
 *  desc   : 网络请求的异常类
 *  modify :
 * </pre>
 */

public class LazyException {

    private static final int BAD_REQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    private static final int ACCESS_DENIED = 302;
    private static final int HANDEL_ERROR = 417;


    public static LazyThrowable handleException(java.lang.Throwable e) {
        String detail = "";
        if (e.getCause() != null) {
            detail = e.getCause().getMessage(); // 异常信息
        }

        LazyThrowable ex;

        if (!(e instanceof LazyRuntimeException) && e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new LazyThrowable(e, httpException.code());

            switch (ex.getCode()) {
                case BAD_REQUEST:
                    ex.setMessage(LazyExceptionTipConfig.get().getBadRequest());
                    break;
                case UNAUTHORIZED:
                    ex.setMessage(LazyExceptionTipConfig.get().getUnauthorized());
                    break;
                case FORBIDDEN:
                    ex.setMessage(LazyExceptionTipConfig.get().getForbidden());
                    break;
                case NOT_FOUND:
                    ex.setMessage(LazyExceptionTipConfig.get().getNotFound());
                    break;
                case REQUEST_TIMEOUT:
                    ex.setMessage(LazyExceptionTipConfig.get().getRequestTimeout());
                    break;
                case GATEWAY_TIMEOUT:
                    ex.setMessage(LazyExceptionTipConfig.get().getGatewayTimeout());
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.setMessage(LazyExceptionTipConfig.get().getInternalServerError());
                case BAD_GATEWAY:
                    ex.setMessage(LazyExceptionTipConfig.get().getBadGateway());
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.setMessage(LazyExceptionTipConfig.get().getServiceUnavailable());
                    break;
                case ACCESS_DENIED:
                    ex.setMessage(LazyExceptionTipConfig.get().getAccessDenied());
                    break;
                case HANDEL_ERROR:
                    ex.setMessage(LazyExceptionTipConfig.get().getHandelError());
                    break;
                default:
                    if (TextUtils.isEmpty(ex.getMessage())) {
                        ex.setMessage(e.getMessage());
                        break;
                    }

                    if (TextUtils.isEmpty(ex.getMessage()) && e.getLocalizedMessage() != null) {
                        ex.setMessage(e.getLocalizedMessage());
                        break;
                    }

                    if (TextUtils.isEmpty(ex.getMessage())) {
                        ex.setMessage(LazyExceptionTipConfig.get().getUnknownError());
                    }
                    break;
            }
            return ex;
        } else if (e instanceof LazyRuntimeException) {
            LazyRuntimeException resultException = (LazyRuntimeException) e;
            ex = new LazyThrowable(resultException, resultException.code);
            ex.setMessage(resultException.getMessage());
            return ex;
        } else if (e instanceof com.alibaba.fastjson.JSONException
                || e instanceof org.json.JSONException
                || e instanceof ParseException) {
            ex = new LazyThrowable(e, ERROR.PARSE_ERROR);
            ex.setMessage(LazyExceptionTipConfig.get().getParseError());
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new LazyThrowable(e, ERROR.NETWORD_ERROR);
            ex.setMessage(LazyExceptionTipConfig.get().getConnError());
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new LazyThrowable(e, ERROR.SSL_ERROR);
            ex.setMessage(LazyExceptionTipConfig.get().getSslError());
            return ex;
        } else if (e instanceof java.security.cert.CertPathValidatorException) {
            ex = new LazyThrowable(e, ERROR.SSL_NOT_FOUND);
            ex.setMessage(LazyExceptionTipConfig.get().getSslNotFound());
            return ex;
        } else if (e instanceof SSLPeerUnverifiedException) {
            ex = new LazyThrowable(e, ERROR.SSL_NOT_FOUND);
            ex.setMessage(LazyExceptionTipConfig.get().getSslInvalid());
            return ex;

        } else if (e instanceof ConnectTimeoutException) {
            ex = new LazyThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.setMessage(LazyExceptionTipConfig.get().getConnTimeout());
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new LazyThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.setMessage(LazyExceptionTipConfig.get().getSocketTimeout());
            return ex;
        } else if (e instanceof java.lang.ClassCastException) {
            ex = new LazyThrowable(e, ERROR.FORMAT_ERROR);
            ex.setMessage(LazyExceptionTipConfig.get().getClassCast());
            return ex;
        } else if (e instanceof NullPointerException) {
            ex = new LazyThrowable(e, ERROR.NULL);
            ex.setMessage(LazyExceptionTipConfig.get().getNullPoint());
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new LazyThrowable(e, NOT_FOUND);

            if (!RxNetTool.isNetworkAvailable(LazyConfig.get().getContext())) {
                ex.setMessage(LazyExceptionTipConfig.get().getInvalidNetWork());
                return ex;
            }

            ex.setMessage(LazyExceptionTipConfig.get().getUnknownHostname());
            return ex;
        } else if (e instanceof NetworkOnMainThreadException) {
            ex = new LazyThrowable(e);
            ex.setMessage(LazyExceptionTipConfig.get().getNetworkOnMain());
            return ex;
        } else {
            ex = new LazyThrowable(e, UNKNOWN);

            if (!TextUtils.isEmpty(e.getMessage())) {
                ex.setMessage(e.getMessage());
            } else if (!TextUtils.isEmpty(e.getLocalizedMessage())) {
                ex.setMessage(e.getLocalizedMessage());
            } else {
                ex.setMessage(LazyExceptionTipConfig.get().getUnknownError());
            }
            return ex;
        }
    }

    /**
     * 约定异常
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;

        /**
         * 证书未找到
         */
        public static final int SSL_NOT_FOUND = 1007;

        /**
         * 出现空值
         */
        public static final int NULL = -100;

        /**
         * 格式错误
         */
        public static final int FORMAT_ERROR = 1008;
    }
}
