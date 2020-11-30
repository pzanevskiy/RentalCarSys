<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 15.05.2020
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-success" >
    <a class="navbar-brand mb-1">RentalCar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="LoginServlet">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="PreselectServlet">Filters</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="ViewCarsServlet">View cars</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-white"  id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Menu
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="btn btn-outline-light rounded-pill dropdown-item" href="ViewAwaitOrdersServlet">View awaiting orders</a>
                    <a class="btn btn-outline-light rounded-pill dropdown-item" href="ViewCurrentOrdersServlet">View current orders</a>
                    <a class="btn btn-outline-light rounded-pill dropdown-item" href="ViewCanceledOrdersServlet">View canceled orders</a>
                    <a class="btn btn-outline-light rounded-pill dropdown-item" href="ViewComplOrdersServlet">View completed orders</a>
                    <a class="btn btn-outline-light rounded-pill dropdown-item" href="ViewInvoicesServlet">View invoices for auto repair</a>
                </div>
            </li >
        </ul>
        <div class="row">
            <div class="col-auto">
                <a class="navbar-text text-white" style="text-decoration: none;">
                    <span class="font-weight-bold">User: ${user.name}</span>
                </a>
            </div>
            <div class="col-auto">
                <a class="navbar-text text-white" style="text-decoration: none;">
                    <span class="font-weight-bold">Email: ${user.email}</span>
                </a>
            </div>
            <div class="col-auto">
                <a class="navbar-text text-white" style="text-decoration: none;">
                    <span class="font-weight-bold">Money: ${user.money}</span>
                </a>
            </div>
            <div class="col-auto">
                <form class="form-inline my-2 my-lg-0">
                    <a href="LogoutServlet">
                        <button class="btn btn-outline-light border border-light rounded-pill" formaction="LogoutServlet" type="submit">
                            <span>
                                Logout
                            </span>
                        </button>
                    </a>
                </form>
            </div>
        </div>
    </div>
</nav>

</body>
</html>
