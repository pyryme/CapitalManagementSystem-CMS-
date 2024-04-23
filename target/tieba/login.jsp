<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>登录</h2>
<% if (request.getParameter("error") != null) { %>
<p style="color: red;">用户名或者密码错误.</p>
<% } %>
<p>由于没有连接数据库，这里的用户名和密码都是模拟的，分别为testName,123456.</p>
<form action="User/login" method="post">
    用户名: <input type="text" name="username"><br>
    密码: <input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>
<p>还没有账号? <a href="register.jsp">注册</a></p>
</body>
</html>
