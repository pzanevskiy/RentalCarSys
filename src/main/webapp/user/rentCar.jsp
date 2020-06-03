<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 19.05.2020
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Rent car</title>
</head>
<body>
<jsp:include page="user.jsp" />
<div class="view_content1">
    <form action="RentCarServlet" method="post">
        <p>Brand: ${car.name}</p>
        <p>Model: ${car.model}</p>
        <p>Type: ${car.type}</p>
        <p>Price: ${car.price}</p>
        <p>Duration: <input type="text" name="dur" autocomplete="off" pattern="[0-9]{1,}"></p>
        <p>
            <input type="hidden" name="id" value="${car.id}">
            <input type="submit" value="Rent car" class="button">
            <a href="ViewCarsServlet"><input type="button" value="Back to store"></a>
        </p>
    </form>
</div>
</body>
</html>

