# Todo
## Version 0.1
实现一个只能浏览列表的客户端。

* [ ] 初始化项目 - Stardust
* [ ] 获取到版块列表并显示 - Stardust
* [ ] 显示板块简介、主题、帖子数量 - Kikyo
* [ ] 显示板块图标 - Stardust
* [ ] 点击板块后，获取到板块的主题列表(包括浏览数、帖子数)并显示 - Kikyo
* [ ] 界面优化: 使界面更加美观 - Stardust
* [ ] 增加侧拉菜单
	* [ ] 菜单上方头像、背景, 下方设置、退出 - Stardust
	* [ ] 菜单列表: 板块、最新、热门, 并实现点击菜单列表后显示各个子项目 - Kikyo
	* [ ] 子项目: 最新、热门 (版块在前面已经实现) - Stardust

## Refactor1

* [ ] 分层。将界面和业务逻辑分开，整理好项目目录架构 - Stardust, Kikyo
* [ ] 初步使用AA, ButterKnife 框架优化界面、控制层代码 - Stardust, Kikyo
* [ ] 使用Retrofit框架代替丑陋的HttpUrlConnection对REST API的调用 - Stardust, Kikyo
* [ ] 初步认识RxJava (Reative开发)(在此之前可能需要先补充一下函数式编程的基本思想) - Kikyo

## Version 0.2

实现一个可以登录、可以浏览帖子内容的客户端。

* [ ] 用户登录及其界面
* [ ] 登录后头像、昵称的获取并显示在侧拉菜单中
* [ ] 点击头像获取到用户的基本资料并显示
* [ ] 增加侧拉菜单/设置的注销登录选项并实现
* [ ] 点击主题显示帖子内容
* [ ] 优化界面设计

## Refactor2

* [ ] 使用图片加载库代替手工图片获取和加载(Glide, Passio等)
* [ ] 广泛使用RxJava, AA, ButterKnife
* [ ] 参考其他客户端改进架构和代码(例如第三方微博客户端[WeiBo](https://github.com/wenmingvs/WeiBo))

## Version 0.3

实现一个发帖、回复、修改资料的客户端。第一个可以日常使用的客户端。

* [ ] 在版块主题列表中发表新主题
* [ ] 在帖子详情页中对帖子内容进行直接回复(暂不考虑楼中楼)
* [ ] 点击主题列表、帖子内容的用户头像时跳转到该用户资料页
* [ ] 允许用户修改自己的文字资料并保存
* [ ] 允许用户上传头像并保存
* [ ] 在右上角增加搜索图标并实现搜索界面、搜索结果的实现
* [ ] 用户注册界面及功能

## Refactor3 

...
(待续)