<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 18.05.2020
  Time: 13:42
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
<jsp:include page="user.jsp" />
<div class="container my-3 mx-auto">
    <div class="row m-2">
        <div class="col-12 text-center">
            <h1>Awaiting orders</h1>
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
                               <div class="col">Order#${order.id}</div>
                               <div class="col">Car - ${order.car.name}</div>
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
<%--                                   <div class="mb-1 row">--%>
<%--                                       <label for="end" class="col col-form-label">Rent's end</label>--%>
<%--                                       <div class="col">--%>
<%--                                           <input type="text" disabled class="form-control-plaintext" id="end" value="${order.endDate}">--%>
<%--                                       </div>--%>
<%--                                   </div>--%>
                               </div>
                               </div>
                           </div>
                       </div>
                   </div>
                       <div class="card-footer">
                           <form action="" method="post">
                               <input type="hidden" name="ids" value="${order.id}"/>
                               <input type="submit" value="Cancel order" class="btn btn-primary">
                           </form>
                       </div>
                   </div>
               </div>
           </div>
       </c:forEach>
    </div>
    </div>
</div>
</body>
</html>
