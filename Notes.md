# 笔记

## 第一次重构

### AA & ButterKnife


AA框架是用一些Java注解来简化代码，提高生产力的框架。比如通过Click注解代替setOnClickListener, 通过ViewById代替findViewById。大概相当于前端的jQuery。


ButterKnite和AA一样，但更灵活，不像AA只能用于指定的那些类（Activity, Fragment等), 像ViewHolder呀之类的ButterKnife都能用（AA也支持ViewHolder但是用起来麻烦）。唯一的缺点是要手动调用一次绑定函数。

一般来说，能用AA的直接用AA，不能的话再用ButterKnife。AA在一些地方比ButterKnife方便一点。


### Retrofit

是一个非常方便的调用REST API的框架。（https://www.zhihu.com/question/28557115) 是一组设计良好的API。

使用方法：

* 新建一个XXApi的接口对应要调用的Api, 比如CategoryApi
* 在接口中添加方法，使用注解来说明如何调用这个Api。比如获取目录我们是对地址`/api/categories`发起GET请求。
```
@GET("api/categories")
Call<Categories> getCategories();
```
* 通过Retrofit.create创建这个接口的对象并调用接口。只要加上GsonConvertor，就能把请求自动解析为相应对象。

Retrofit的方便对请求参数、POST参数等等都可以很方便的设置。结合后面的RxJava更是超级方便。

### 项目结构

* ui/ 界面层。主要是各种Activity和Fragment，以及自定义的View等等。
        Activity和Fragment在MVC中既充当View的角色也充当Controller的角色。
        但是要把View和Controller分开。尽量让Activity和Fragment只做C层。
      ui包中包括了各个功能的界面，分别在不同的子包中。比如main(主界面), login(登录)等。
      common包中是一些和项目密切相关的但又是ui各个功能共用的部分。

* network/ 网络层。向服务器请求相应的资源并接收服务器的响应。
	* entity/ 实体。在网络层中即和服务器交互的数据的抽象。比如Users, User等。
			  要注意实体和ViewModel的区别。
			  一般来说不会直接用网络请求返回的实体Entity作为ViewModel去呈现View，但在安卓中由于ViewHolder的存在这里并不是MVVM的模式，
			  因此可以直接把Entity给Adapter等呈现数据。
	* api/ 和服务器交互的接口。主要是调用服务器的接口。
	* NodeBBService 网络层的对其他层的暴露的入口。主要是管理各种网络层的调用。

* util/ 工具类。注意的是能不把类放这里就不要放。因为"工具"是一个很泛的概念。比如图片加载，如果涉及的逻辑比较复杂，需要多个类，就另外新建一个image包。

* widget/ widget包放置一些通用的自定义View获取和View密切相关的辅助类等。和ui不同的是，这里放置的是通用的，不限于该项目的View等。

### 代码规范、设计与实现细节
* 命名。实际上PostList应该改为TopicList。
* public字段问题。
* 裸写字符串问题。主要是为了后续维护、翻译（如果要增加多语言支持）方便。
  包括在代码中的字符串和在布局文件中的字符串。
* 变量名称的规范问题。一般来说在Java中，私有变量以m开头，静态static变量为s开头，
  常量为大写。这样会和Java和Android本身的源码风格一致。尤其是在团队中统一的代码规范比较重要。
* 抽象复用与尽量遵守单一权责：
 	一个类负责一件事，与这个类关系不大的写在另一个类里。例如从网址加载就不应该写在AvatarView或者其他类中而是写一个ImageLoader之类的工具类。这同时也是为了复用。
 	* 自定义View 
 	* 工具类
	* 常量类。除了工具类外的一种特殊类，用来存储和管理一些常量。注意这些常量必须是公用的。如果只是类内部的常量，直接放在类里既可。
	  字体类是非典型的常量类，因为他同时可以管理字体的生命周期（所以也可以说是Manager之类的）。
