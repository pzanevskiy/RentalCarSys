<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.05.2020
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of orders</title>
</head>
<body>
<jsp:include page="user.jsp" />
<div class="view_content">
    <table>
        <caption>Orders</caption>
        <thead>
        <tr>
            <td>ID</td>
            <td>User</td>
            <td>Car</td>
            <td>Duration</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${orders}">
            <tr>
                <td>${item.id}</td>
                <td>${item.user.name}</td>
                <td>${item.car.name}</td>
                <td>${item.duration}</td>
                <td>${item.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
