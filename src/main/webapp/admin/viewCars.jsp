<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 13.05.2020
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of cars</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<script>
    function tableSearch() {
        var phrase = document.getElementById('search-text');
        var table = document.getElementById('info-table');
        var regPhrase = new RegExp(phrase.value, 'i');
        var flag = false;
        for (var i = 1; i < table.rows.length; i++) {
            flag = false;
            for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
                flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
                if (flag) break;
            }
            if (flag) {
                table.rows[i].style.display = "";
            } else {
                table.rows[i].style.display = "none";
            }

        }
    }

    function upd(id) {
        document.getElementById("modalBrand").value = document.getElementById("brand" + id).textContent;
        document.getElementById("modalModel").value = document.getElementById("model" + id).textContent;
        document.getElementById("modalPrice").value = document.getElementById("price" + id).textContent;
        var select = document.getElementById("modalSelect").getElementsByTagName("option");
        for (let i = 0; i < select.length; i++) {
            if (select[i].value === document.getElementById("status" + id)) {
                select[i].selected = true;
            }
        }
        var inp = document.getElementById("modalId");
        inp.setAttribute("value", id);
    }
</script>
<jsp:include page="admin.jsp"/>
<div class="container">
    <div class="row m-2">
        <div class="col-12 text-center">
            <h1>Cars</h1>
        </div>
    </div>
    <div class="row m-2">
        <div class="col-12 text-center">
            <button id="${item.id}" class="btn btn-outline-success rounded-pill"
                    type="button" data-toggle="modal" data-target="#exampleModal2">
                Add new car
            </button>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead class="table-dark">
            <tr>
                <td>ID</td>
                <td>Brand</td>
                <td>Model</td>
                <td>Price</td>
                <td>Status</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cars}">
                <tr>
                    <td class="align-middle"><strong>${item.id}</strong></td>
                    <td class="align-middle" id="brand${item.id}">${item.name}</td>
                    <td class="align-middle" id="model${item.id}">${item.model}</td>
                    <td class="align-middle" id="price${item.id}">${item.price}<span>$</span></td>
                    <td class="align-middle" id="status${item.id}">${item.status}</td>
                    <td class="align-middle">
                        <button id="${item.id}" class="btn btn-outline-warning rounded-pill"
                                type="button" data-toggle="modal" data-target="#exampleModal" onclick="upd(${item.id})">
                            Edit
                        </button>
                    </td>
                    <form action="RemoveCarServlet" method="post">
                    <td class="align-middle">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="submit" value="&times;" class="btn btn-outline-danger rounded-pill">
                    </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Editing car...</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="EditCarServlet" method="post">
                    <div class="modal-body">
                        <div class="mb-3 row">
                            <label for="modalBrand" class="col-sm-2 col-form-label">Brand</label>
                            <div class="col-sm-10">
                                <input type="text" name="brand" autocomplete="off" class="form-control" id="modalBrand"
                                       value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="modalModel" class="col-sm-2 col-form-label">Model</label>
                            <div class="col-sm-10">
                                <input type="text" name="model" autocomplete="off" class="form-control" id="modalModel"
                                       value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="modalPrice" class="col-sm-2 col-form-label">Price</label>
                            <div class="col-sm-10">
                                <input type="text" name="price" autocomplete="off" class="form-control" id="modalPrice"
                                       value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="modalSelect" class="col-sm-2 col-form-label">Status</label>
                            <div class="col-sm-10">
                                <select id="modalSelect" class="form-control" name="status">
                                    <option value="FREE">FREE</option>
                                    <option value="SELECTED">SELECTED</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="hidden" id="modalId" name="id" value="">
                        <input type="submit" value="Edit" class="btn btn-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal2" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add new car...</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="AddCarServlet" method="post">
                    <div class="modal-body">
                        <div class="mb-3 row">
                            <label for="addBrand" class="col-sm-2 col-form-label">Brand</label>
                            <div class="col-sm-10">
                                <input type="text" name="brand" autocomplete="off" class="form-control" id="addBrand"
                                       value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="addModel" class="col-sm-2 col-form-label">Model</label>
                            <div class="col-sm-10">
                                <input type="text" name="model" autocomplete="off" class="form-control" id="addModel"
                                       value="">
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="addPrice" class="col-sm-2 col-form-label">Price</label>
                            <div class="col-sm-10">
                                <input type="text" name="price" autocomplete="off" class="form-control" id="addPrice"
                                       value="">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <input type="submit" value="Add" class="btn btn-success">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
