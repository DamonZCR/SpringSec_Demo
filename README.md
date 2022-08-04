## 简介

| 序号 | 项目名                    |  使用框架  |          安全技术           | 数据库 |
| :--: | ------------------------- | :--------: | :-------------------------: | :----: |
|  1   | springmvc_session         | SpringMVC  | session校验+SpringMVC拦截器 |   无   |
|  2   | springsecurity_demo       | SpringMVC  |       SpringSecurity        |   无   |
|  3   | springboot_security_demo1 | SpringBoot |       SpringSecurity        |   无   |
|  4   | springboot_security_demo3 | SpringBoot |       SpringSecurity        |   有   |
|  5   | springboot_security_demo4 | SpringBoot |       SpringSecurity        |   有   |

- 第一个项目，只是测试使用SpringMVC通过Session实现认证和授权的，目前基于这种的授权已经很少使用，都是使用安全框架来实现，所以这个就是入门。
- 第二个项目，使用SpringMVC集成了Spring Security，进行用户登录的认证和授权，没有实现保持会话的功能，登录界面和退出登录都是使用Spring Security自带的界面。使用基于内存的查询方式；
- 第三个项目，使用SpringBoot集成了Spring Security，这个项目只是简单的实现了使用SpringBoot进行认证和授权，没有实现会话+数据库连接。这个项目的目的就是为了体现SpringBoot集成Spring Security相对 SpringMVC要简单的多，因为需要注解 或者 扫描 注入都不需要表示；对这个项目的增强就是 **springboot_security_demo3** 这个项目；
- 第四个项目，实现用户信息从数据库中查询，但是用户权限仍然是写死不从数据库中获得。实现自定义登录界面，当用户输入网址就跳转到指定的登录界面，输入:项目地址/logout 就直接退出了。
- 第五个项目，实现对用户所具有的权限从数据库中查询，通过Dao中的：findPermissionsByUserId(String userId)方法查询权限，封装到UserDetails中。实现了当用户访问：项目名/logout 就自动跳转到登录表单的操作。在这个项目尝试了web授权和方法授权。未实现，如果用户输入密码错误不能进行提示，对与用户输入的账号不存在将报出异常不能解决。
- 