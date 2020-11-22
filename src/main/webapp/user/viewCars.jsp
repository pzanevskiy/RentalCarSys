<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 17.05.2020
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of cars</title>
</head>
<body>
    <jsp:include page="user.jsp" />
<div class="view_content">
    <div class="container">
        <div class="row row-cols-3">
            <c:forEach var="item" items="${cars}">
                <div class="col">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.model}</td>
                        <td>${item.type}</td>
                        <td>${item.price}</td>
                        <td>${item.status}</td>
                        <td>
                            <form action="RentCarServlet">
                                <input type="hidden" name="id" value="${item.id}">
                                <input type="submit" value="Rent car" class="button">
                            </form>
                        </td>
                    </tr>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
</body>
</html>
