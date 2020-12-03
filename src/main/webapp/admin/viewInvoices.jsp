<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.05.2020
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of invoices</title>
</head>
<body>
<jsp:include page="admin.jsp" />
<div class="container my-3 mx-auto">
    <div class="row m-2">
        <div class="col-12 text-center">
            <h1>Invoices</h1>
        </div>
    </div>
    <div class="accordion" id="accordionExample">
        <c:forEach var="invoice" items="${invoices}">
            <div class="card bg-light border border-success my-1 rounded">
                <div class="card-header bg-light" id="heading${invoice.id}">
                    <h2 class="m-1">
                        <button class="btn btn-light btn-block text-left" type="button" data-toggle="collapse" style="text-decoration: none;"
                                data-target="#collapse${invoice.id}" aria-expanded="false" aria-controls="collapse${invoice.id}">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-xl-2">
                                <div class="col">Invoice#${invoice.id}</div>
                                <div class="col">Price - ${invoice.price}</div>
                            </div>
                        </button>
                    </h2>
                </div>

                <div id="collapse${invoice.id}" class="collapse" aria-labelledby="heading${invoice.id}" data-parent="#accordionExample">
                    <div class="card">
                        <div class="card-body">
                            <div class="row mb-2 row-cols-1 row-cols-xl-1">
                                <div class="col p-1">
                                    <div class="card" >
                                        <div class="card-header">
                                            <h4 class="card-title">Invoice info</h4>
                                        </div>
                                        <div class="card-body">
                                            <div class="mb-1 row">
                                                <label for="invoiceOrderId" class="col col-form-label">Order id</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoiceOrderId" value="${invoice.order.id}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="invoicePrice" class="col col-form-label">Price</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoicePrice" value="${invoice.price}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="invoiceMessage" class="col col-form-label">Message</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoiceMessage" value="${invoice.message}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="invoiceStatus" class="col col-form-label">Status</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoiceStatus" value="${invoice.invoiceStatus}">
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
                                                    <input type="text" disabled class="form-control-plaintext" id="orderDur" value="${invoice.order.duration}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="total" class="col col-form-label">Total price</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="total" value="${invoice.order.duration * invoice.order.car.price}$">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
