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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</head>
<body>
<script src="https://kit.fontawesome.com/56da16082b.js" crossorigin="anonymous"></script>
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
<div class="container">
    <div class="d-flex h-100 justify-content-center align-items-center">
        <div class="card">
            <div class="card-header bg-primary">
                <h3 class="text-white">Sign Up</h3>
            </div>
            <div class="card-body">
                <form action="AddUserServlet" method="post">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" name="name" class="form-control" required placeholder="name">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                        </div>
                        <input type="text" name="email" class="form-control" required placeholder="email"
                               pattern="([a-z0-9-_.]+)@([a-z0-9-_.]+).([a-z]{2,8})">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" name="pass" class="form-control" required placeholder="password"
                               pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}"
                               title="Password must contain 1 uppercase, 1 lowercase and 1 digit symbol. Example: qsAZx111">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" id="chpass" class="form-control" required placeholder="confirm password" >
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-coins"></i></span>
                        </div>
                        <input type="text" name="money" class="form-control" placeholder="money">
                    </div>
                    <div class="form-group">
                        <input type="submit" value="Sign up" class="btn btn-primary btn-large btn-block">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
