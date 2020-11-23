<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 23.11.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 17.05.2020
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of cars</title>
</head>
<body>
<jsp:include page="user.jsp"/>
<div class="container">
    <select class="form-control">
        <c:forEach var="item" items="${names}">
            <option>${item}</option>
        </c:forEach>
    </select>
    <a class="btn btn-outline-light rounded-pill dropdown-item" href="ViewCarsServlet">View cars</a>
</div>
</body>
</html>
