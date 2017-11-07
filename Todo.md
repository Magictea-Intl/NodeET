# Todo
## Version 0.1
实现一个只能浏览列表的客户端。

* [x] 初始化项目 - Stardust
* [x] 获取到版块列表并显示 - Stardust
* [x] 显示板块简介、主题、帖子数量 - Kikyo
* [x] 显示板块图标 - Stardust
* [x] 点击板块后，获取到板块的主题列表(包括浏览数、帖子数)并显示 - Kikyo
* [x] 增加侧拉菜单, 上方头像、背景, 下方设置、退出 - Stardust
* [x] 增加TabLayout, 各个Tab为"版块", "最新", "热门"  - Kikyo
* [x] 完成"最新", "热门"的Tab对应的内容   - Stardust
* [x] 界面优化: 使界面更加丑观 - Stardust

## Refactor1

* [x] 分层。将界面和业务逻辑分开，整理好项目目录架构 - Stardust, Kikyo
* [x] 初步使用AA, ButterKnife 框架优化界面、控制层代码 - Stardust, Kikyo
* [x] 使用Retrofit框架代替丑陋的HttpUrlConnection对REST API的调用 - Stardust, Kikyo

## Version 0.2

实现一个可以登录、可以浏览帖子内容的客户端。

* [ ] 用户登录API - Stardust
* [ ] 用户登录界面 - Kikyo
* [ ] 登录后头像、昵称的获取并显示在侧拉菜单中 - Kikyo
* [ ] 增加侧拉菜单的注销登录选项并实现 - Stardust
* [ ] 点击头像获取到用户的基本资料并显示 - Kikyo
* [ ] 帖子获取API - Kikyo
* [ ] 点击主题显示帖子内容 - Stardust
* [ ] 优化界面设计 - Stardust

## Refactor2

* [ ] 使用图片加载库代替手工图片获取和加载(Glide, Passio等)
* [ ] 初步认识RxJava (在此之前可能需要先补充一下函数式编程的基本思想) - Kikyo
* [ ] 广泛使用AA, ButterKnife
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
