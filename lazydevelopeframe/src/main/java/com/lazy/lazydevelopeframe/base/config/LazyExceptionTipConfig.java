package com.lazy.lazydevelopeframe.base.config;

/**
 * <pre>
 *  author : liming
 *  time   : Created by on 2018/1/30.
 *  desc   : 异常提示语配置类
 *  modify :
 * </pre>
 */

public class LazyExceptionTipConfig {

    private static LazyExceptionTipConfig config;

    public static LazyExceptionTipConfig init() {
        if (config == null) {
            synchronized (LazyExceptionTipConfig.class) {
                if (config == null) {
                    config = new LazyExceptionTipConfig();
                }
            }
        }
        return config;
    }

    public static LazyExceptionTipConfig get() {
        if (config == null) throw new NullPointerException("please call first init() method");
        return config;
    }

    private String invalidNetWork = "请检查网络是否连接";
    private String badRequest = "错误的请求";
    private String unauthorized = "未授权的请求";
    private String forbidden = "禁止访问";
    private String notFound = "服务器地址未找到";
    private String requestTimeout = "请求超时";
    private String gatewayTimeout = "网关响应超时";
    private String internalServerError = "服务器内部异常";
    private String badGateway = "无效的请求";
    private String serviceUnavailable = "服务器不可用";
    private String accessDenied = "拒绝访问";
    private String handelError = "接口处理失败";
    private String unknownError = "未知错误";
    private String parseError = "解析错误";
    private String connError = "连接失败";
    private String sslError = "证书验证失败";
    private String sslNotFound = "证书路径未找到";
    private String sslInvalid = "证书无效";
    private String connTimeout = "连接超时";
    private String socketTimeout = "连接超时";
    private String classCast = "类型转换出错";
    private String nullPoint = "空数据";
    private String unknownHostname = "服务器地址未找到,请检查网络或url";
    private String networkOnMain = "耗时操作不允许放在主线程中";

    public LazyExceptionTipConfig setInvalidNetWork(String invalidNetWork) {
        this.internalServerError = invalidNetWork;
        return this;
    }

    public LazyExceptionTipConfig setUnauthorized(String unauthorized) {
        this.unauthorized = unauthorized;
        return this;
    }

    public LazyExceptionTipConfig setBadRequest(String badRequest) {
        this.badRequest = badGateway;
        return this;
    }

    public LazyExceptionTipConfig setForbidden(String forbidden) {
        this.forbidden = forbidden;
        return this;
    }

    public LazyExceptionTipConfig setNotFound(String notFound) {
        this.notFound = notFound;
        return this;
    }

    public LazyExceptionTipConfig setRequestTimeout(String requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public LazyExceptionTipConfig setGatewayTimeout(String gatewayTimeout) {
        this.gatewayTimeout = gatewayTimeout;
        return this;
    }

    public LazyExceptionTipConfig setInternalServerError(String internalServerError) {
        this.internalServerError = internalServerError;
        return this;
    }

    public LazyExceptionTipConfig setBadGateway(String badGateway) {
        this.badGateway = badGateway;
        return this;
    }

    public LazyExceptionTipConfig setServiceUnavailable(String serviceUnavailable) {
        this.serviceUnavailable = serviceUnavailable;
        return this;
    }

    public LazyExceptionTipConfig setAccessDenied(String accessDenied) {
        this.accessDenied = accessDenied;
        return this;
    }

    public LazyExceptionTipConfig setHandelError(String handelError) {
        this.handelError = handelError;
        return this;
    }

    public LazyExceptionTipConfig setUnknownError(String unknownError) {
        this.unknownError = unknownError;
        return this;
    }

    public LazyExceptionTipConfig setParseError(String parseError) {
        this.parseError = parseError;
        return this;
    }

    public LazyExceptionTipConfig setConnError(String connError) {
        this.connError = connError;
        return this;
    }

    public LazyExceptionTipConfig setSslError(String sslError) {
        this.sslError = sslError;
        return this;
    }

    public LazyExceptionTipConfig setSslNotFound(String sslNotFound) {
        this.sslNotFound = sslNotFound;
        return this;
    }

    public LazyExceptionTipConfig setSslInvalid(String sslInvalid) {
        this.sslInvalid = sslInvalid;
        return this;
    }

    public LazyExceptionTipConfig setConnTimeout(String connTimeout) {
        this.connTimeout = connTimeout;
        return this;
    }

    public LazyExceptionTipConfig setSocketTimeout(String socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public LazyExceptionTipConfig setClassCast(String classCast) {
        this.classCast = classCast;
        return this;
    }

    public LazyExceptionTipConfig setNullPoint(String nullPoint) {
        this.nullPoint = nullPoint;
        return this;
    }

    public LazyExceptionTipConfig setUnknownHostname(String unknownHostname) {
        this.unknownHostname = unknownHostname;
        return this;
    }

    public LazyExceptionTipConfig setNetworkOnMain(String networkOnMain) {
        this.networkOnMain = networkOnMain;
        return this;
    }

    public String getUnauthorized() {
        return unauthorized;
    }

    public String getForbidden() {
        return forbidden;
    }

    public String getNotFound() {
        return notFound;
    }

    public String getRequestTimeout() {
        return requestTimeout;
    }

    public String getGatewayTimeout() {
        return gatewayTimeout;
    }

    public String getInternalServerError() {
        return internalServerError;
    }

    public String getBadGateway() {
        return badGateway;
    }

    public String getServiceUnavailable() {
        return serviceUnavailable;
    }

    public String getAccessDenied() {
        return accessDenied;
    }

    public String getHandelError() {
        return handelError;
    }

    public String getUnknownError() {
        return unknownError;
    }

    public String getParseError() {
        return parseError;
    }

    public String getConnError() {
        return connError;
    }

    public String getSslError() {
        return sslError;
    }

    public String getSslNotFound() {
        return sslNotFound;
    }

    public String getSslInvalid() {
        return sslInvalid;
    }

    public String getConnTimeout() {
        return connTimeout;
    }

    public String getSocketTimeout() {
        return socketTimeout;
    }

    public String getClassCast() {
        return classCast;
    }

    public String getNullPoint() {
        return nullPoint;
    }

    public String getUnknownHostname() {
        return unknownHostname;
    }

    public String getNetworkOnMain() {
        return networkOnMain;
    }

    public String getBadRequest() {
        return badRequest;
    }

    public String getInvalidNetWork() {
        return invalidNetWork;
    }

    @Override
    public String toString() {
        return "LazyExceptionTipConfig{" +
                "invalidNetWork='" + invalidNetWork + '\'' +
                ", badRequest='" + badRequest + '\'' +
                ", unauthorized='" + unauthorized + '\'' +
                ", forbidden='" + forbidden + '\'' +
                ", notFound='" + notFound + '\'' +
                ", requestTimeout='" + requestTimeout + '\'' +
                ", gatewayTimeout='" + gatewayTimeout + '\'' +
                ", internalServerError='" + internalServerError + '\'' +
                ", badGateway='" + badGateway + '\'' +
                ", serviceUnavailable='" + serviceUnavailable + '\'' +
                ", accessDenied='" + accessDenied + '\'' +
                ", handelError='" + handelError + '\'' +
                ", unknownError='" + unknownError + '\'' +
                ", parseError='" + parseError + '\'' +
                ", connError='" + connError + '\'' +
                ", sslError='" + sslError + '\'' +
                ", sslNotFound='" + sslNotFound + '\'' +
                ", sslInvalid='" + sslInvalid + '\'' +
                ", connTimeout='" + connTimeout + '\'' +
                ", socketTimeout='" + socketTimeout + '\'' +
                ", classCast='" + classCast + '\'' +
                ", nullPoint='" + nullPoint + '\'' +
                ", unknownHostname='" + unknownHostname + '\'' +
                ", networkOnMain='" + networkOnMain + '\'' +
                '}';
    }
}
