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
    <meta charset="UTF-8">
    <title>List of cars</title>
</head>
<body>
<jsp:include page="user.jsp"/>
<div class="container">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-xl-3 ">
        <c:forEach var="item" items="${cars}">
            <div class="col p-2">
                <div class="card">
                    <div class="card-header">
                        <h4 id="brand${item.id}" class="card-title">${item.name}</h4>
                    </div>
                    <div class="card-body">
                        <p>Model:<span id="model${item.id}"> ${item.model}</span></p>
                        <p>Price:<span id="price${item.id}"> ${item.price}$</span></p>
                        <p>Status: ${item.status}</p>
                            <%--                            <form>--%>
                            <%--                            <form action="RentCarServlet">--%>
                            <%--                                <input type="hidden" name="id" value="${item.id}">--%>
                            <%--                                <button id="btn1" class="btn btn-dark border border-dark rounded-pill"--%>
                            <%--                                        type="submit" >--%>
                            <%--                                    Rent car--%>
                            <%--                                </button>--%>
                            <%--                            </form>--%>
                        <button id="${item.id}" class="btn btn-dark border border-dark rounded-pill stretched-link"
                                type="button" data-toggle="modal" data-target="#exampleModal" onclick="upd(${item.id})">
                            Rent car
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalBrand"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="RentCarServlet" method="post">
                    <div class="modal-body">
                        <%--                        <p id="modalModel">Model: </p>--%>
                        <div class="mb-3 row">
                            <label for="modalModel" class="col-sm-2 col-form-label">Model</label>
                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control-plaintext" id="modalModel" value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="modalPrice" class="col-sm-2 col-form-label">Price</label>
                            <div class="col-sm-10">
                                <input type="text" disabled class="form-control-plaintext" id="modalPrice" value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="modalStartDate" class="col-sm-2 col-form-label">Start</label>
                            <div class="col-sm-10">
                                <input type="datetime-local" name="start" required
                                       class="form-control" id="modalStartDate">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="modalEndDate" class="col-sm-2 col-form-label">End</label>
                            <div class="col-sm-10">
                                <input type="datetime-local" name="end" required
                                       class="form-control" id="modalEndDate">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="hidden" id="modalId" name="id" value="">
                        <input type="submit" value="Rent car" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function upd(id) {
        document.getElementById("modalBrand").textContent = document.getElementById("brand" + id).textContent;
        document.getElementById("modalModel").value = document.getElementById("model" + id).textContent;
        document.getElementById("modalPrice").value = document.getElementById("price" + id).textContent;
        var inp = document.getElementById("modalId");
        inp.setAttribute("value", id);
    }
</script>
</html>
