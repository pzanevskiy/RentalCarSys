<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.05.2020
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of orders</title>
</head>
<body>
<jsp:include page="admin.jsp" />
<div class="container my-3 mx-auto">
    <div class="row m-2">
        <div class="col-12 text-center">
            <h1>Returned orders</h1>
        </div>
    </div>
    <div class="accordion" id="accordionExample">
        <c:forEach var="order" items="${orders}">
            <div class="card bg-light border border-success my-1 rounded">
                <div class="card-header bg-light" id="heading${order.id}">
                    <h2 class="m-1">
                        <button class="btn btn-light btn-block text-left" type="button" data-toggle="collapse" style="text-decoration: none;"
                                data-target="#collapse${order.id}" aria-expanded="false" aria-controls="collapse${order.id}">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-xl-2">
                                <div class="col">Order#<span id="order${order.id}">${order.id}</span></div>
                                <div class="col">Car - ${order.car.name}</div>
                                <div class="col">Rent's start - ${order.startDate}</div>
                                <div class="col">Rent's end - ${order.endDate}</div>
                            </div>
                        </button>
                    </h2>
                </div>

                <div id="collapse${order.id}" class="collapse" aria-labelledby="heading${order.id}" data-parent="#accordionExample">
                    <div class="card">
                        <div class="card-body">
                            <div class="row mb-2 row-cols-1 row-cols-xl-1">
                                <div class="col p-1">
                                    <div class="card" >
                                        <div class="card-header">
                                            <h4 class="card-title">User info</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="mb-1 row">
                                                <label for="userName" class="col col-form-label">Name</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="userName" value="${order.user.name}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="userEmail" class="col col-form-label">Email</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="userEmail" value="${order.user.email}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col p-1">
                                    <div class="card" >
                                        <div class="card-header">
                                            <h4 class="card-title">Car info</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="mb-1 row">
                                                <label for="carName" class="col col-form-label">Brand</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="carName" value="${order.car.name}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="carModel" class="col col-form-label">Model</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="carModel" value="${order.car.model}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="carPrice" class="col col-form-label">Price</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="carPrice" value="${order.car.price}$">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col p-1">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Order info</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="mb-1 row">
                                                <label for="orderDur" class="col col-form-label">Duration</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="orderDur" value="${order.duration}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="total" class="col col-form-label">Total price</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="total" value="${order.duration * order.car.price}$">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="orderStatus" class="col col-form-label">Order status</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="orderStatus" value="${order.status}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="start" class="col col-form-label">Rent's start</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="start" value="${order.startDate}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="end" class="col col-form-label">Rent's end</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="end" value="${order.endDate}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <div class="row row-cols-sm-2 row-cols-xl-2">
                                <div class="col text-center">
                                    <form action="" method="post">
                                        <input type="hidden" name="id" value="${order.id}"/>
                                        <input type="hidden" name="status" value="complete">
                                        <input type="submit" value="Complete order" class="btn btn-success">
                                    </form>
                                </div>
                                <div class="col text-center">
                                    <button id="${order.id}" class="btn btn-danger"
                                            type="button"  data-toggle="modal" data-target="#exampleModal" onclick="upd(${order.id})">
                                        Invoice order
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Order</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="" method="post">
                <div class="modal-body">
                    <div class="mb-3 row">
                        <label for="modalOrderID" class="col col-form-label">ID</label>
                        <div class="col-sm-10">
                            <input type="text" disabled class="form-control-plaintext" id="modalOrderID" value="">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="modalPen" class="col-sm-2 col-form-label">Penalty</label>
                        <div class="col-sm-10">
                            <input type="text" name="repair" autocomplete="off" required class="form-control" id="modalPen">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="modalMsg" class="col-sm-2 col-form-label">Message</label>
                        <div class="col-sm-10">
                            <input type="text" name="msg" autocomplete="off" class="form-control" id="modalMsg" value="">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="hidden" id="modalId" name="id" value="">
                    <input type="hidden" name="status" value="invoice">
                    <input type="submit" value="Send invoice" class="btn btn-primary">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    function upd(id) {
        document.getElementById("modalOrderID").value=document.getElementById("order"+id).textContent;
        var inp=document.getElementById("modalId");
        inp.setAttribute("value",id);
    }
</script>
<%--<div class="view_content">--%>
<%--    <table>--%>
<%--        <caption>Orders</caption>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <td>ID</td>--%>
<%--            <td>User</td>--%>
<%--            <td>Car</td>--%>
<%--            <td>Duration</td>--%>
<%--            <td>Status</td>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <c:forEach var="item" items="${orders}">--%>
<%--            <tr>--%>
<%--                <td>${item.id}</td>--%>
<%--                <td>${item.user.name}</td>--%>
<%--                <td>${item.car.name}</td>--%>
<%--                <td>${item.duration}</td>--%>
<%--                <td>${item.status}</td>--%>
<%--                <td>--%>
<%--                    <form action="AddInvoiceServlet">--%>
<%--                        <input type="hidden" name="id" value="${item.id}"/>--%>
<%--                        <input type="hidden" name="status" value="repair">--%>
<%--                        <input type="submit" value="Invoice" class="button">--%>
<%--                    </form>--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <form action="" method="post">--%>
<%--                        <input type="hidden" name="id" value="${item.id}"/>--%>
<%--                        <input type="hidden" name="status" value="completed">--%>
<%--                        <input type="submit" value="Complete order" class="button">--%>
<%--                    </form>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>
<%--</body>--%>
</html>