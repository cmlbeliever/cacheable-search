
# cacheable-search
参数缓存框架，将页面跳转需要的参数缓存后方便后续使用。

在项目中，查询条件保持是经常使用到的，特别是管理后台。对于前台页面来说，通常为了访问的方便会使用get的方式进行表单提交，这样进行页面分享或者发送给好友时可以直接打开对于的页面。但是对于管理后台来说，地址栏上的一大串url参数是不允许的，不美观也不安全。 

比如在用户查询页面，可以根据用户的年龄，姓名，昵称，等等参数进行查询，而且可能客户已经翻到了第n页上，此时点击某个用户详细，页面跳转到用户详细页面对用户信息进行编辑，编辑完成后点击保存，这时候需要返回到用户查询页面上，并且还得回到用户原来页面。

基本流程：
![流程图][1]

## 具体的项目演进与分析过程，详见：

 - gitchat：**http://gitbook.cn/gitchat/activity/5a52d334ebd9cc598adf1258**
 - 博客说明：http://blog.csdn.net/cml_blog/article/details/78928693



# 使用场景 #

- **需要在跳转其他页面后，返回当前页面还需要参数保持的情况**
- **新增数据需要跳转到预览或确认页面的情况**
- **需要缓存参数的情况**


 
## 如何使用？

 - 添加依赖：

``` stylus
	<dependency>
		<groupId>com.github.cmlbeliever</groupId>
		<artifactId>cacheable-search-mvc</artifactId>
		<version>:lastVersion</version>
	</dependency>
```

最新从maven仓库获取：http://search.maven.org/#search%7Cga%7C1%7Ccmlbeliever

 - 在Controller中添加注解：@SearchCache

``` stylus
 @RequestMapping("/list")
    public String userList(Model model, @SearchCache() User u) {
        return "user-list";
    }
```

 - 获取cacheToken

    可以从HttpServletRequest中直接获取到cacheToken，如：
    request.getAttribute("cacheToken")

 

## Sample使用教程 ##
- 进入CacheableSearchMVCSample工程
- 执行SearchCacheMVCApplication
- 等待启动完成

## 测试 ##

**注：cacheToken每次都会随机生成，这里需要根据实际情况调用。为了测试方便，这里使用get的方式进行说明。**

项目启动后，访问：http://localhost:8080/user/list?age=123&name=zhangsan 可以看到页面输出：

    searchParam:
	User [age=123, name=zhangsan]
	cacheToken:
	90508986-33b6-44cd-a869-7be8de2612ed
	myToken:

将cacheToken作为参数，访问：http://localhost:8080/user/list?cacheToken=90508986-33b6-44cd-a869-7be8de2612ed 可以看到之前请求的参数缓存下来了：

    searchParam:
	User [age=123, name=zhangsan]
	cacheToken:
	90508986-33b6-44cd-a869-7be8de2612ed
	myToken:

关闭浏览器后，再次打开访问http://localhost:8080/user/list?cacheToken=90508986-33b6-44cd-a869-7be8de2612ed 可以看到缓存的信息已经没有了，数据被清空了。

## BUG、意见、反馈 ##
如果您在使用中有任何的bug或意见，可以在项目添加issue或者在gitchat添加评论，我会及时对项目及时修正，**持续维护中...**

## 版本记录 ##

- 2.0

> 添加支持application/json（同@RequestBody）方式请求

- 1.1

> 去除lib中无用的依赖，将dependency的scope修改为provided

- 1.0

>初始化版本 



  [1]: ./snapshot/cache.png "cache.png"
