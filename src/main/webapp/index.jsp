<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Welcome page</title>
</head>
<body>

<div class="login">
    <div class="login-screen">
        <div class="app-title">
            <h1>Login</h1>
        </div>

        <div class="login-form">
            <form action="LoginServlet" method="post">
                <input type="text" class="login-input" name="email" placeholder="Email" id="login-name">
                <input type="password" class="login-input" name="password" placeholder="password" id="login-pass">
                <input type="submit" class="btn btn-primary btn-large btn-block" value="login">
            </form>
            <form>
                <a class="login-link" href="AddUserServlet">Create account</a>
            </form>
        </div>
    </div>
</div>

<%--<div class="view_content">--%>
<%--        <div>--%>
<%--            <div class="name">Name:</div>--%>
<%--            <div class="password">Password:</div>--%>
<%--        </div>--%>
<%--        <form action="LoginServlet" method="post">--%>
<%--            <div class="inputs">--%>
<%--                <input name="email" type="text" placeholder="Email" id="login" required/>--%>
<%--                <input name="password" type="password" placeholder="Password" id="password" required/>--%>
<%--            </div>--%>
<%--            <div><input type="submit" value="Log In" /></div>--%>
<%--        </form>--%>
<%--        <form action="AddUserServlet">--%>
<%--            <div><input type="submit" value="Reg user" ></div>--%>
<%--        </form>--%>
<%--</div>--%>
</body>
</html>