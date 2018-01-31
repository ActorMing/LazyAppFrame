# LazyAppFrame
android 快速开发框架
该框引用了以下库<br/>
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

   -optimizationpasses 5<br/>
   -dontusemixedcaseclassnames<br/>
-dontskipnonpubliclibraryclasses<br/>
-dontskipnonpubliclibraryclassmembers<br/>
-dontpreverify<br/>
-verbose<br/>
-ignorewarning<br/>
-printmapping proguardMapping.txt<br/>
-optimizations !code/simplification/cast,!field/*,!class/merging/*<br/>
-keepattributes *Annotation*,InnerClasses<br/>
-keepattributes Signature<br/>
-keepattributes SourceFile,LineNumberTable<br/>

##### 默认保留区

-keep public class * extends android.app.Activity<br/>
-keep public class * extends android.app.Application<br/>
-keep public class * extends android.app.Service<br/>
-keep public class * extends android.content.BroadcastReceiver<br/>
-keep public class * extends android.content.ContentProvider<br/>
-keep public class * extends android.app.backup.BackupAgentHelper<br/>
-keep public class * extends android.preference.Preference<br/>
-keep public class * extends android.view.View<br/>
-keep public class com.android.vending.licensing.ILicensingService<br/>
-keep class android.support.** {*;}<br/>

-keepclasseswithmembernames class * {<br/>
    native <methods>;<br/>
}<br/>
-keepclassmembers class * extends android.app.Activity{<br/>
    public void *(android.view.View);<br/>
}<br/>
-keepclassmembers enum * {<br/>
    public static **[] values();<br/>
    public static ** valueOf(java.lang.String);<br/>
}<br/>
-keep public class * extends android.view.View{<br/>
    *** get*();<br/>
    void set*(***);<br/>
    public <init>(android.content.Context);<br/>
    public <init>(android.content.Context, android.util.AttributeSet);<br/>
    public <init>(android.content.Context, android.util.AttributeSet, int);<br/>
}<br/>
-keepclasseswithmembers class * {<br/>
    public <init>(android.content.Context, android.util.AttributeSet);<br/>
    public <init>(android.content.Context, android.util.AttributeSet, int);<br/>
}<br/>
-keep class * implements android.os.Parcelable {<br/>
  public static final android.os.Parcelable$Creator *;<br/>
}<br/>
-keepclassmembers class * implements java.io.Serializable {<br/>
    static final long serialVersionUID;<br/>
    private static final java.io.ObjectStreamField[] serialPersistentFields;<br/>
    private void writeObject(java.io.ObjectOutputStream);<br/>
    private void readObject(java.io.ObjectInputStream);<br/>
    java.lang.Object writeReplace();<br/>
    java.lang.Object readResolve();<br/>
}<br/>
-keep class **.R$* {<br/>
 *;<br/>
}<br/>
-keepclassmembers class * {<br/>
    void *(**On*Event);<br/>
}<br/>

##### fastjson

-keep class com.alibaba.fastjson.** { *; }<br/>

-dontwarn android.support.**<br/>
-dontwarn com.alibaba.fastjson.**<br/>

##### OkHttp3

-dontwarn okhttp3.logging.**<br/>
-keep class okhttp3.internal.**{*;}<br/>
-dontwarn okio.**<br/>

##### Retrofit

-dontwarn retrofit2.**<br/>
-keep class retrofit2.** { *; }<br/>
-keepattributes Signature<br/>
-keepattributes Exceptions<br/>

##### RxJava RxAndroid

-dontwarn sun.misc.** <br/>
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {<br/>
    long producerIndex;<br/>
    long consumerIndex;<br/>
}<br/>
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {<br/>
    rx.internal.util.atomic.LinkedQueueNode producerNode;<br/>
}<br/>
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {<br/>
    rx.internal.util.atomic.LinkedQueueNode consumerNode;<br/>
}<br/>

##### bean

-keep class com.lazy.lazydevelopeframe.bean.** {*;}<br/>
