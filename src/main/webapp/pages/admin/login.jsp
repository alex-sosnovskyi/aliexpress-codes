<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.04.2017
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Login</title>
</head>
<body>
<h1> </h1>
<form name="adminLogin" action="${pageContext.servletContext.contextPath}/admin-panel" align ="center" method="post">
    <label> User <input name="login" type="text" ></label><br>
    <br>
    <label>Password <input name="password" type="password"></label>
    <input type="submit" value="LogIn">
</form>
</body>
</html>