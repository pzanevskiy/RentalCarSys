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
    <title>List of orders</title>
</head>
<body>
<jsp:include page="user.jsp"/>
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
                                                    <input type="text" disabled class="form-control-plaintext" id="invoicePrice" value="${invoice.price}$">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="invoiceMessage" class="col col-form-label">Message</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoiceMessage" value="${invoice.message}">
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
                        <div class="card-footer">
                            <form action="" method="post">
                                <input type="hidden" name="id" value="${invoice.id}"/>
                                <input type="submit" value="Pay" class="btn btn-success">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row m-2">
        <div class="col-12 text-center">
            <h1>Paid invoices</h1>
        </div>
    </div>
    <div class="accordion" id="accordionExample2">
        <c:forEach var="invoiceP" items="${invoicesP}">
            <div class="card bg-light border border-success my-1 rounded">
                <div class="card-header bg-light" id="heading${invoiceP.id}">
                    <h2 class="m-1">
                        <button class="btn btn-light btn-block text-left" type="button" data-toggle="collapse" style="text-decoration: none;"
                                data-target="#collapse${invoiceP.id}" aria-expanded="false" aria-controls="collapse${invoiceP.id}">
                            <div class="row row-cols-1 row-cols-sm-2 row-cols-xl-2">
                                <div class="col">Invoice#${invoiceP.id}</div>
                                <div class="col">Price - ${invoiceP.price}</div>
                            </div>
                        </button>
                    </h2>
                </div>

                <div id="collapse${invoiceP.id}" class="collapse" aria-labelledby="heading${invoiceP.id}" data-parent="#accordionExample2">
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
                                                <label for="invoiceOrderIdP" class="col col-form-label">Order id</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoiceOrderIdP" value="${invoiceP.order.id}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="invoicePriceP" class="col col-form-label">Price</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoicePriceP" value="${invoiceP.price}$">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="invoiceMessageP" class="col col-form-label">Message</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="invoiceMessageP" value="${invoiceP.message}">
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
                                                <label for="orderDurP" class="col col-form-label">Duration</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="orderDurP" value="${invoiceP.order.duration}">
                                                </div>
                                            </div>
                                            <div class="mb-1 row">
                                                <label for="totalP" class="col col-form-label">Total price</label>
                                                <div class="col">
                                                    <input type="text" disabled class="form-control-plaintext" id="totalP" value="${invoiceP.order.duration * invoiceP.order.car.price}$">
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
