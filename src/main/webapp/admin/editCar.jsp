<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 17.05.2020
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit page</title>
</head>
<body>
<jsp:include page="admin.jsp" />
<div class="view_content1">
    <form action="EditCarServlet" method="post">
        <p>ID: ${car.id}</p>
        <p>Brand: <input type="text" name="brand" autocomplete="off" value="${car.name}" pattern="^[a-zA-Z]+$"></p>
        <p>Model: <input type="text" name="model" autocomplete="off" value="${car.model}"></p>
        <p>Type: <input type="text" name="type" autocomplete="off" value="${car.type}" pattern="^[a-zA-Z]+$"></p>
        <p>Price: <input type="text" name="price" autocomplete="off" value="${car.price}" pattern="[0-9]{1,}"></p>
        <p>Status:
            <input type="hidden" name="status" value="${car.status}">
            <select name="status">
                <option hidden disabled selected>${car.status}</option>
                <option value="FREE">FREE</option>
                <option value="SELECTED">SELECTED</option>
            </select>
        </p>

        <p>
            <input type="hidden" name="id" value="${car.id}">
            <input type="submit" value="Edit car" class="button">
        </p>
    </form>
</div>
</body>
</html>
