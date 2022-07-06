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

如果以后想将这个SpringBoot项目独立出来，需要删除这个指定SpringSec_Demo父项目依赖（源代码框2），将SpringBoot的父依赖（源代码框1）放到springBoot_security_demo项目依赖中.

## 项目简介

这个项目只是简单的实现了使用SpringBoot进行认证和授权，没有实现会话+数据库连接。

这个项目的目的就是为了体现SpringBoot集成Spring Security相对 SpringMVC要简单的多，因为需要注解 或者 扫描 注入都不需要表示；

对这个项目的增强就是 **springboot_security_demo3** 这个项目；