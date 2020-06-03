<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 15.05.2020
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="admin_menu">
    <a href="ViewCarsServlet">View cars</a>
    <a href="ViewAwaitOrdersServlet">View awaiting orders</a>
    <a href="ViewCurrentOrdersServlet">View current orders</a>
    <a href="ViewCanceledOrdersServlet">View canceled orders</a>
    <a href="ViewComplOrdersServlet">View completed orders</a>
    <a href="ViewInvoicesServlet">View invoices for auto repair</a>
    <a href="LogoutServlet">Logout</a>
    <p>User: ${user.name}<span> Email: ${user.email}</span></p>
</div>



</body>
</html>
