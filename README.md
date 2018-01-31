# LazyAppFrame
android 快速开发框架
该框引用了以下库<br/>
[Dagger2](https://github.com/google/dagger "依赖注入框架")、[RxTools](https://github.com/vondear/RxTools "工具类集合")、
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
