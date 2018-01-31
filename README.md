# LazyAppFrame
android 快速开发框架
该框引用了以下库(相关库的文档可以点击下方的链接进行查看学习)<br/>
[Dagger2](https://github.com/google/dagger "依赖注入框架配合mvp同时使用让整个项目耦合性更低,方便拓展")、[RxTools](https://github.com/vondear/RxTools "工具类集合")、
[Alerter](https://github.com/Tapadoo/Alerter "顶部alert提示")、[fastjson](https://github.com/alibaba/fastjson "解析json数据")、
[RxAndroid](https://github.com/ReactiveX/RxAndroid "一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库")
<br/>[Retrofit](https://github.com/square/retrofit "一个针对Android和Java类型安全的http客户端")、[retrofit-converter-fastjson](https://github.com/ligboy/retrofit-converter-fastjson "retrofit json 转换适配器") 、[retrofit-adapters-rxjava2](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2 "retrofit对rxjava 的支持")、converter-scalars字符串转换适配器、[OkHttpInterceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor "okHttp 拦截器")

## How to use
### To get a Git project into your build
#### Step 1. Add the JitPack repository to your build file
    Add it in your root build.gradle at the end of repositories:
    
    allprojects {
	  repositories {
		...
		maven { url 'https://jitpack.io' }
	  }
     }

#### Step 2. Add the dependency
     dependencies {
     		compile 'com.github.ActorMing:LazyAppFrame:v1.0.0'
     }

### 在项目中的应用


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
