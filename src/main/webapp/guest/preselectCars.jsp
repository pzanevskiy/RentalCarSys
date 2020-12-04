<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 04.12.2020
  Time: 17:39
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
<jsp:include page="guest.jsp"/>
<div class="container">
    <form action="PreselectServlet" method="post">
        <div class="row justify-content-center">
            <div class="col-xl-5 col-md-4 col-xs-2 my-2">
                <input id="txt" name="val" type="hidden">
                <h3 class="m-3">Select brand</h3>
                <select id="sel" class="form-control m-3" onchange="update()">
                    <option class="m-1 rounded=pill" selected><--choose--></option>
                    <option class="m-1 rounded-pill" value="*" >All</option>
                    <c:forEach var="item" items="${names}">
                        <option class="m-1 rounded-pill" value="${item}">${item}</option>
                    </c:forEach>
                </select>
                <input class="btn btn-secondary rounded-pill m-3" value="Submit" type="submit"/>
            </div>
            <div class="col-xl-5 col-md-4 col-xs-2 my-2">
                <h3 class="m-3">Select price</h3>
                <input class="m-2" name="range" type="text" id="textInput" value="" placeholder="Price..." readonly>
                <input min="10" max="1000" type="range" class="form-control-range m-3" oninput="updateTextInput(this.value)" id="formControlRange">
            </div>
        </div>

    </form>

</div>
</body>
<script>
    function updateTextInput(val) {
        document.getElementById('textInput').value=val;
    }
    function update() {
        var d=document.getElementById("sel");
        var dtext=d.options[d.selectedIndex].value;
        document.getElementById("txt").value=dtext;
    }
</script>
</html>
