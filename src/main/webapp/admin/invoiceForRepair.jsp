<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 19.05.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Invoice for repair</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<jsp:include page="admin.jsp" />
<div class="view_content1">
    <form action="AddInvoiceServlet" method="post">
        <div class="view_content1">
            <p>ID: ${order.id}</p>
            <p>Duration: ${order.duration}</p>
            <p>Repair price: <input type="text" name="repair" autocomplete="off" pattern="[0-9]{1,}"></p>
            <p>Message: <input type="text" name="msg" autocomplete="off" pattern="^[a-zA-Z]+$"></p>
            <p>
                <input type="hidden" name="id" value="${order.id}">
                <input type="submit" value="Send invoice" class="button">
                <a href="ViewReturnedOrdersServlet"><input type="button" value="Back to returned orders"></a>
            </p>
        </div>
        <div class="view_content1">
            <p>Car</p>
            <p>Brand: ${order.car.name}</p>
            <p>Model: ${order.car.model}</p>
            <p>Type: ${order.car.type}</p>
            <p>Price: ${order.car.price}</p>
        </div>
        <div class="view_content1">
            <p>User</p>
            <p>Name: ${order.user.name}</p>
            <p>Email: ${order.user.email}</p>
        </div>
    </form>
</div>
</body>
</html>
