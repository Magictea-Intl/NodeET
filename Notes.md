# 笔记

## 第一次重构

### AA & ButterKnife

我们先从比较简单的Android Annoations和ButterKnife注解框架开始。

AA框架是用一些Java注解来简化代码，提高生产力的框架。比如通过Click注解代替setOnClickListener, 通过ViewById代替findViewById。大概相当于前端的jQuery。

因为这个阶段的布局还不复杂，所以我们只是初步使用了，可以在后面的界面编写中慢慢熟悉。

ButterKnite呢和AA是一样的，但更灵活，不像AA只能用于指定的那些类（Activity, Fragment等), 像ViewHolder呀之类的ButterKnife都能用（AA也支持ViewHolder但是用起来麻烦）。唯一的缺点是要手动调用一次绑定函数。

一般来说，能用AA的直接用AA，不能的话再用ButterKnife。AA在一些地方比ButterKnife方便一点。

> 然后注解和绑定的就这样啦是不是蛮简单的~~

### Retrofit

然后第二个是Retrofit。这是一个非常方便的调用REST API的框架。什么是RESTful API呢~ (看知乎链接ing....https://www.zhihu.com/question/28557115) 其实就是一组设计良好的API而已。

使用Retrofit对这些Api的调用非常方便~ 

* 新建一个XXApi的接口对应要调用的Api, 比如CategoryApi
* 在接口中添加方法，使用注解来说明如何调用这个Api。比如获取目录我们是对地址`/api/categories`发起GET请求。就写
```
@GET("api/categories")
Call<Categories> getCategories();
```
* 通过Retrofit.create创建这个接口的对象并调用接口。只要加上GsonConvertor，就能把请求自动解析为相应对象。

当然Retrofit的方便不仅仅在于此。对请求参数、POST参数等等都可以很方便的设置。结合后面的RxJava更是超级方便。