<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 21.09.2019
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Добавление купона</title>
</head>
<body>
<form name="adminLogin" action="${pageContext.servletContext.contextPath}/admin-panel/statistic/add" align="center"
      method="post">
    <label> Name <input name="name" type="text"></label><br>
    <br>
    <label>Description <input name="description" type="text"></label><br>
    <br>
    <label> Code <input name="code" type="text"></label><br>
    <br>
    <label>Link <input name="link" type="text"></label><br>
    <br>
    <label> Region <input name="region" type="text"></label><br>
    <br>
    <label>Day On <input name="dayOn" type="date"></label><br>
    <br>
    <label>Day Of <input name="dayOf" type="date"></label><br>
    <br>
    <label>Rating <input name="rating" type="text"></label><br>
    <p> </p>
    <input type="submit" value="Добавить">
</form>
</body>
</html>
