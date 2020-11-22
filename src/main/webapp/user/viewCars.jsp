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
<%--                <div class="col">--%>
<%--                    <div class="card" style="width: 18rem;">--%>
<%--                        <img src="" class="card-img-top" alt="${item.name}">--%>
<%--                        <div class="card-body">--%>
<%--                            <h5 class="card-title">Название карточки</h5>--%>
<%--                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>--%>
<%--                            <a href="#" class="btn btn-primary">Go somewhere</a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="col">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.model}</td>
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
