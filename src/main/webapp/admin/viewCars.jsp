<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 13.05.2020
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored ="false" %>
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
</script>
<jsp:include page="admin.jsp" />
<div class="view_content">
    <input type="text" placeholder="Search" id="search-text" onkeyup="tableSearch()">
    <table id="info-table">
        <caption>Cars</caption>
        <thead>
        <tr>
            <td>ID</td>
            <td>Brand</td>
            <td>Price</td>
            <td>Status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${cars}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.model}</td>
                <td>${item.price}</td>
                <td>${item.status}</td>
                <td>
                    <form action="EditCarServlet">
                    <input type="hidden" name="ide" value="${item.id}">
                    <input type="submit" value="Edit car" class="button">
                    </form>
                </td>
                <td>
                    <form action="RemoveCarServlet" method="post">
                        <input type="hidden" name="id" value="${item.id}">
                        <input type="submit" value="Remove car" class="button">
                    </form>
                </td>
            </tr>

        </c:forEach>
        <form action="AddCarServlet" method="post">
            <tr>
                <td>N/A</td>
                <td><input type="text" name="brand"></td>
                <td><input type="text" name="model"></td>
                <td><input type="text" name="price"></td>
                <td>FREE</td>
                <td><input type="submit" value="Add car" class="button"></td>
            </tr>
        </form>
        </tbody>
    </table>
</div>
</body>
</html>
