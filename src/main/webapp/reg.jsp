<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 21.05.2020
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<script>
    function valid(event){
        var pas = document.getElementById('pass').value
        var cpas = document.getElementById('chpass').value
        for(i=0;i < cpas.length; i++)
        {

            if(pas[i] != cpas[i] && event.keyCode != 8)
            {
                alert('Пароли не совпадают');
                break;
            }
        }
    }
</script>
<div class="login">
    <div class="login-screen">
        <div class="app-title">
            <h1>Registration</h1>
        </div>
        <div class="login-form">
            <form action="AddUserServlet" method="post">
                <p><input type="text" class="login-input" name="name" placeholder="Enter username" required></p>
                <p><input type="text" class="login-input" name="email" placeholder="Enter email" required
                                 pattern="([a-z0-9-_.]+)@([a-z0-9-_.]+).([a-z]{2,8})"></p>
                <p><input id="pass" type="password" class="login-input" name="pass" placeholder="Enter password"
                                    required pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}"
                                    title="Password must contain 1 uppercase, 1 lowercase and 1 digit symbol. Example: qsAZx111"></p>
                <p><input id="chpass" class="login-input" type="password" placeholder="Repeat password" required onkeyup="valid(event)"></p>
                <p><input type="text" class="login-input" name="money" placeholder="Enter amount" required pattern="[0-9]{1,7}"></p>
                <p><input type="submit" class="btn btn-primary btn-large btn-block" value="Create account" class="button"></p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
