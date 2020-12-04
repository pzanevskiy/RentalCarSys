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
<div class="container">
    <div class="row m-2">
        <div class="col-12 text-center">
            <h1>Users</h1>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="table-dark">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Email</td>
                <td>Money</td>
                <td>Status</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${users}">
                <tr>
                    <td class="align-middle"><strong>${item.id}</strong></td>
                    <td class="align-middle" id="name${item.id}">${item.name}</td>
                    <td class="align-middle" id="email${item.id}">${item.email}</td>
                    <td class="align-middle" id="money${item.id}">${item.money}<span>$</span></td>
                    <td class="align-middle" id="status${item.id}">${item.status}</td>
                    <form action="BanUserServlet" method="post">
                        <td class="align-middle">
                            <input type="hidden" name="id" value="${item.id}">
                            <input type="submit" value="Ban/Unban user" class="btn btn-info rounded-pill">
                        </td>
                    </form>

                    <form action="DeleteUserServlet" method="post">
                        <td class="align-middle">
                            <input type="hidden" name="id" value="${item.id}">
                            <input type="submit" value="&times;" class="btn btn-danger rounded-pill">
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
