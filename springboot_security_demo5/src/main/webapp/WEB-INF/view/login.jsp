<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<form action="login" method="post">
    用户名：<input type="text" name="username"><br>
    密&nbsp;&nbsp;&nbsp;码:
    <input type="password" name="password"><br>
    <c:if test="${param.error==0}">
        <p style="color: mediumvioletred">账号或密码错误</p>
    </c:if>
    <input type="submit" value="登录">
</form>
</body>
</html>