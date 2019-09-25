<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 21.09.2019
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Редактирование купона</title>
</head>
<body>
<form name="adminLogin" action="${pageContext.servletContext.contextPath}/admin-panel/statistic/edit" align="center"
      method="post">
    <input type="text" name="id" value="<c:out value="${editCoupon.id}" default=" "/>" hidden="hidden">
    <label> Name <input name="name" type="text" value="<c:out value="${editCoupon.name}" default=" "/>"/></label><br>
    <br>
    <label>Description <input name="description" type="text" value="<c:out value="${editCoupon.description}" default=" "/>"></label><br>
    <br>
    <label> Code <input name="code" type="text" value="<c:out value="${editCoupon.code}" default=" "/>"></label><br>
    <br>
    <label>Link <input name="link" type="text" value="<c:out value="${editCoupon.link}" default=" "/>"></label><br>
    <br>
    <label> Region <input name="region" type="text" value="<c:out value="${editCoupon.region}" default=" "/>"></label><br>
    <br>
    <label>Day On <input name="dayOn" type="date" value="<c:out value="${editCoupon.startOn}" default=" "/>"></label><br>
    <br>
    <label>Day Of <input name="dayOf" type="date" value="<c:out value="${editCoupon.endOf}" default=" "/>"></label><br>
    <br>
    <label>Rating <input name="rating" type="text" value="<c:out value="${editCoupon.rating}" default=" "/>"></label><br>
    <p> </p>
    <input type="submit" value="Сохранить изменения">
</form>
</body>
</html>
