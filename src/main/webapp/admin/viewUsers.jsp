<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 17.05.2020
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of users</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="admin.jsp" />
<div class="view_content">
    <table>
        <caption>User</caption>
        <thead>
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Email</td>
            <td>Password</td>
            <td>Money</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${users}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.email}</td>
                <td>${item.password}</td>
                <td>${item.money}</td>
                <td>${item.status}</td>
                <td>
                    <form action="BanUserServlet" method="post">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="submit" value="Ban/Unban user" class="button">
                    </form>
                </td>
                <td>
                    <form action="DeleteUserServlet" method="post">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="submit" value="Delete user" class="button">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
