注：因为所有的SpringBoot项目都需要添加一个父项目依赖：

```java
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.3.RELEASE</version>
</parent>
```

​    但是由于我的这个项目module建在了SpringSec_Deno中了，所以再这个项目下建的module都是这个项目的子项目，所以这里新建的子项目springBoot_security_demo项目就需要有一个依赖：


```java
<parent>
    <artifactId>SpringSec_Deno</artifactId>
    <groupId>com.SpringSec.damo</groupId>
    <version>1.0-SNAPSHOT</version>
</parent>
```

但是maven 规定一个项目只能有一个父项目，所以只能将SpringBoot的父项目依赖放到SpringSec_Demo项目的依赖文件中。

如果以后想将这个SpringBoot项目独立出来，需要删除这个指定SpringSec_Demo父项目依赖（源代码框2），将SpringBoot的父依赖（源代码框1）放到springBoot_security_demo3项目依赖中.

## 简介

实现对用户所具有的权限从数据库中查询，通过Dao中的：findPermissionsByUserId(String userId)方法查询权限，封装到UserDetails中。实现了当用户访问：项目名/logout 就自动跳转到登录表单的操作。在这个项目尝试了web授权和方法授权。未实现，如果用户输入密码错误不能进行提示，对与用户输入的账号不存在将报出异常不能解决，但是解决了当用户输入错误的账号或者密码会给前端返回一个url上带着error=1的参数，前端根据这个参数判断提示用户账号或者密码错误；

