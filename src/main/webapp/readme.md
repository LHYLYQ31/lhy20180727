plugins - 项目中用到的第三方js插件库，比如bootstrap和jquery
|__ boostrap - bootstrap库(若该插件库还有css样式文件和皮肤或者图片资源，则统一放到该目录下)
|__ jquery -jquery库

common - 公共封装的js和公用html模板放置于该目录下
|__ js - 项目中多个功能模块用到的公共js
|__ util.js – 工具类方法
|__ properties.js 全局常量
|__ config.js整个项目的通用配置
|__ html - 项目中多个功能模块用到的html模板

modules - 以功能模块命名子文件夹放置于该目录下，比如：user-manage(用户管理)：
|__ user-manage
    |__ js - 该功能模块自己编写的js代码
    |__ css- 该功能模块使用到的css样式文件
    |__ images- 该功能模块使用到的图片资源
    |__ xx1.html-该功能模块所有的html文件
|__ xx2.html-该功能模块所有的html文件

data – 该功能模块下面放置.json文件（本地模拟的后端接口返回报文）：
|__ xx1.json 
|__ xx2.json 
static - 项目中静态资源
|__ css - 全局css放置于该路径，必须按照如下文件名命名css
    |__base.css - 基本css 定义全局字体大小、间距、清除浮动等全局基本样式
    |__layout.css - 布局css 定义页面布局，比如导航、左侧菜单、页脚等布局样式
|__modules.css - 通用css 定义页面公共模块、公共组件样式，比如列表、表单、弹出框等通用组件样式
|__themes.css – 实现换肤功能时应用 
|__mend.css – 补丁，基于以上样式进行的私有化修改

|__ fonts - 项目中字体图标文件
|__ images- 项目中公共图片资源

product - 应用支撑平台产品中间资源(只有引用支撑平台产品的项目需要建立此目录，此目录中的内容不允许修改)
|__ workflow - 工作流
|__ user - 统一用户

prototype - 美工提供的切图、美工或者前端工程师编写的静态页面，需要在该目录下保留
|__ 切图 - 美工切图
|__ 页面 - 根据切图实现的静态页面

login.html/index.html/main.html-项目入口文件 登录页面login.html/网站首页一般命名为index.html/后台管理系统手首页命名为main.html
