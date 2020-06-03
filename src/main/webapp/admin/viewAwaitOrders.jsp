<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 17.05.2020
  Time: 18:34
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
<jsp:include page="admin.jsp" />
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
                <td>
                    <form action="" method="post">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <input type="hidden" name="status" value="accept">
                        <input type="submit" value="Accept order" class="button">
                    </form>
                </td>
                <td>
                    <form action="" method="post">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <input type="hidden" name="status" value="reject">
                        <input type="submit" value="Reject order" class="button">
                    </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
