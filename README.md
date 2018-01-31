# LazyAppFrame
android 快速开发框架
该框引用了以下库(相关库的文档可以点击下方的链接进行查看学习)<br/>
[Dagger2](https://github.com/google/dagger "依赖注入框架配合mvp同时使用让整个项目耦合性更低,方便拓展")、[RxTools](https://github.com/vondear/RxTools "工具类集合")、
[Alerter](https://github.com/Tapadoo/Alerter "顶部alert提示")、[fastjson](https://github.com/alibaba/fastjson "解析json数据")、
[RxAndroid](https://github.com/ReactiveX/RxAndroid "一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库")
<br/>[Retrofit](https://github.com/square/retrofit "一个针对Android和Java类型安全的http客户端")、[retrofit-converter-fastjson](https://github.com/ligboy/retrofit-converter-fastjson "retrofit json 转换适配器") 、[retrofit-adapters-rxjava2](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2 "retrofit对rxjava 的支持")、converter-scalars字符串转换适配器、[OkHttpInterceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor "okHttp 拦截器")

## 怎样使用
### 在你的项目中进行构建
#### 第一步. 添加 JitPack 仓库到你的配置文件中
    在你 Project 的 build.gradle 的进行添加:
    
    allprojects {
	  repositories {
		...
		maven { url 'https://jitpack.io' }
	  }
     }

#### 第二步.添加这个引用 (版本号可在 release 中查找最新版本进行替换)
    在你 Module 的 build.gradle 中进行添加:
	
     dependencies {
     		compile 'com.github.ActorMing:LazyAppFrame:v1.0.5'
     }

### 在项目中的应用
##### 在Application中进行初始化
    // Retrofit 相关配置 (可自行选择配置或继续使用默认配置)
    LazyConfig.init()
               .init(this)
               .setBaseHttpUrl("https://www.baidu.com") // 必须要进行设置的 baseUrl
               .setCacheFileName("cacheFileName") // 缓存文件名称 非必填(默认:okHttpCacheFile)
               .setCacheFileSize(10 * 1024 * 1024) // 缓存文件大小 非必填 (默认:10MB)
               .setConnTimeout(15) // 连接超时时间 非必填 (默认:15s)
               .setReadTimeout(15) // 读取超时时间 非必填 (默认:15s)
               .setWriteTimeout(15) // 写入超时时间 非必填 (默认:15s)
               .setHttpRetry(false) // 重试 非必填 (默认:false)
               .setResponseOk(200); // 返回正确的状态码 非必填 (默认:200)

    // 异常提示配置 (所有的提示语都有默认值,可以自行选择配置)
    LazyExceptionTipConfig.init()
    	        .setInvalidNetWork("请检查网络是否连接")
                .setBadRequest("错误的请求")
                .setUnauthorized("未授权的请求")
                .setForbidden("禁止访问")
                .setNotFound("服务器地址未找到")
                .setRequestTimeout("请求超时,请稍后重试")
                .setGatewayTimeout("网关响应超时")
                .setInternalServerError("服务器内部异常")
                .setBadGateway("无效的请求")
                .setServiceUnavailable("服务器不可用")
                .setAccessDenied("拒绝访问")
                .setHandelError("接口处理失败")
                .setUnknownError("位置错误")
                .setParseError("数据解析错误")
                .setConnError("连接失败")
                .setSslError("证书验证失败")
                .setSslNotFound("证书路径未找到")
                .setSslInvalid("证书无效")
                .setConnTimeout("连接超时")
                .setSocketTimeout("连接超时")
                .setClassCast("类型转换错误")
                .setNullPoint("空数据")
                .setUnknownHostname("服务器地址未找到,请检查网络或url")
                .setNetworkOnMain("耗时操作不允许放在主线程中");

#### 代码混淆
##### 基本指令区

    -optimizationpasses 5
    -dontusemixedcaseclassnames
    -dontskipnonpubliclibraryclasses
    -dontskipnonpubliclibraryclassmembers
    -dontpreverify
    -verbose
    -ignorewarning
    -printmapping proguardMapping.txt
    -optimizations !code/simplification/cast,!field/*,!class/merging/*
    -keepattributes *Annotation*,InnerClasses
    -keepattributes Signature
    -keepattributes SourceFile,LineNumberTable

##### 默认保留区

	-keep public class * extends android.app.Activity
	-keep public class * extends android.app.Application
	-keep public class * extends android.app.Service
	-keep public class * extends android.content.BroadcastReceiver
	-keep public class * extends android.content.ContentProvider
	-keep public class * extends android.app.backup.BackupAgentHelper
	-keep public class * extends android.preference.Preference
	-keep public class * extends android.view.View
	-keep public class com.android.vending.licensing.ILicensingService
	-keep class android.support.** {*;}

	-keepclasseswithmembernames class * {
	    native <methods>;
	}
	-keepclassmembers class * extends android.app.Activity{
	    public void *(android.view.View);
	}
	-keepclassmembers enum * {
	    public static **[] values();
	    public static ** valueOf(java.lang.String);
	}
	-keep public class * extends android.view.View{
	    *** get*();
	    void set*(***);
	    public <init>(android.content.Context);
	    public <init>(android.content.Context, android.util.AttributeSet);
	    public <init>(android.content.Context, android.util.AttributeSet, int);
	}
	-keepclasseswithmembers class * {
	    public <init>(android.content.Context, android.util.AttributeSet);
	    public <init>(android.content.Context, android.util.AttributeSet, int);
	}
	-keep class * implements android.os.Parcelable {
	  public static final android.os.Parcelable$Creator *;
	}
	-keepclassmembers class * implements java.io.Serializable {
	    static final long serialVersionUID;
	    private static final java.io.ObjectStreamField[] serialPersistentFields;
	    private void writeObject(java.io.ObjectOutputStream);
	    private void readObject(java.io.ObjectInputStream);
	    java.lang.Object writeReplace();
	    java.lang.Object readResolve();
	}
	-keep class **.R$* {
		 *;
	}
	-keepclassmembers class * {
	    void *(**On*Event);
	}

##### fastjson

	-keep class com.alibaba.fastjson.** { *; }

	-dontwarn android.support.**
	-dontwarn com.alibaba.fastjson.**

##### OkHttp3

	-dontwarn okhttp3.logging.**
	-keep class okhttp3.internal.**{*;}
	-dontwarn okio.**

##### Retrofit

	-dontwarn retrofit2.**
	-keep class retrofit2.** { *; }
	-keepattributes Signature	
	-keepattributes Exceptions

##### RxJava RxAndroid

	-dontwarn sun.misc.** 
	-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
	    long producerIndex;
	    long consumerIndex;
	}
	-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
	    rx.internal.util.atomic.LinkedQueueNode producerNode;
	}
	-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
	    rx.internal.util.atomic.LinkedQueueNode consumerNode;
	}

##### bean

	-keep class com.lazy.lazydevelopeframe.bean.** {*;}
