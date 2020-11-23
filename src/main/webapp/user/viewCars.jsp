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
        <div class="row row-cols-1 row-cols-sm-2 row-cols-xl-3 ">
            <c:forEach var="item" items="${cars}">
                <div class="col p-2">
                    <div class="card" >
                        <div class="card-header">
                            <h4 class="card-title">${item.name}</h4>
                        </div>
                        <div class="card-body">
                            <p>Model: ${item.model}</p>
                            <p>Price: ${item.price}$</p>
                            <p>Status: ${item.status}</p>
                            <form action="RentCarServlet">
                                <input type="hidden" name="id" value="${item.id}">
                                <button id="btn1" class="btn btn-dark border border-dark rounded-pill stretched-link" type="submit">
                                    Rent car
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
