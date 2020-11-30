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
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Rent car</title>
</head>
<body>
<jsp:include page="user.jsp" />
<div class="container">
    <div class="offset-xl-2 col-xl-8 col-lg-12 col-md-12 col-sm-12 col-12 mt-3">
        <div class="card">
            <div class="card-header p-4">
                <div class="float-right">
                    <h3 class="mb-0">Invoice #${orderId}</h3>
                    ${begin}
                </div>
            </div>
            <div class="card-body">
                <div class="row mb-4">
                    <div class="col-sm-6">
                        <h5 class="mb-3">From:</h5>
                        <h3 class="text-dark mb-1">RentCar</h3>
                        <div>22, Ozheshko st.</div>
                        <div>Email: pavel.zanevsky15@gmail.com</div>
                        <div>Phone: +375 (29) 888 3230</div>
                    </div>
                    <div class="col-sm-6 ">
                        <h5 class="mb-3">To:</h5>
                        <h3 class="text-dark mb-1">${user.name}</h3>
                        <div>Email: ${user.email}</div>
                        <div>Rent's start: ${begin}</div>
                        <div>Rent's end: ${end}</div>
                    </div>
                </div>
                <div class="table-responsive-sm">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Brand</th>
                            <th>Model</th>
                            <th class="right">Price</th>
                            <th class="center">Duration</th>
                            <th class="right">Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="left strong">${car.name}</td>
                            <td class="left">${car.model}</td>
                            <td class="right">${car.price}$</td>
                            <td class="center">${dur}</td>
                            <td class="right">${total}$</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer bg-white">
                <p class="mb-0">RentCar.com, Belarus, Grodno</p>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-sm-12 text-center m-1">
                <a class="btn btn-outline-success rounded-pill" href="PreselectServlet">Go to new cars</a>
            </div>
            <div class="col-sm-12 text-center m-1">
                <a class="btn btn-outline-success rounded-pill" href="ViewCarsServlet">Continue with current filters</a>
            </div>
        </div>
    </div>

</div>

</body>
</html>

