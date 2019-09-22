<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Aleksandr
  Date: 20.09.2019
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Statistic</title>
</head>
<body>
<h1 align="center">Статистика используемых купонов</h1>
<table border="1" align="center">
    <caption>Таблица купонов</caption>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Code</th>
        <th>Link</th>
        <th>Region</th>
        <th>Start Day</th>
        <th>End Day</th>
        <th>Rating</th>
        <th>EDIT</th>
        <th>DELETING</th>
    </tr>
    <c:forEach items="${coupons}" var="coupon" varStatus="status">
        <tr>
            <td><c:out value="${coupon.name}" default=" "/></td>
            <td><c:out value="${coupon.description}" default=" "/></td>
            <td><c:out value="${coupon.code}" default=" "/></td>
            <td><c:out value="${coupon.link}" default=" "/></td>
            <td><c:out value="${coupon.region}" default=" "/></td>
            <td><c:out value="${coupon.startOn}" default=" "/></td>
            <td><c:out value="${coupon.endOf}" default=" "/></td>
            <td><c:out value="${coupon.rating}" default=" "/></td>
            <th><a href="${pageContext.servletContext.contextPath}/admin-panel/statistic/edit?id=${coupon.id}">Редактировать</a></th>
            <th><a href="${pageContext.servletContext.contextPath}/admin-panel/statistic/delete?id=${coupon.id}">Удалить</a></th>
        </tr>
    </c:forEach>
</table>

<p></p>
<p><a href="${pageContext.servletContext.contextPath}/admin-panel/statistic/add">Добавить купон</a> </p>
</body>
</html>
