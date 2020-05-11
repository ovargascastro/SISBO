<%-- 
    Document   : login
    Created on : 29/01/2020, 06:02:38 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>LogIn</title>
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
        <link rel="stylesheet" href="assets/css/styles.css">

        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
    </head>

    <body>
        <%@ include file="/presentation/header.jsp" %>
        <div class="login-dark">
            <form action="javascript:login()">
                <h2 class="sr-only">Login Form</h2>
                <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
                <div class="form-group"><input class="form-control" type="text" name="user" placeholder="Usuario" required id="user"></div>
                <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Contraseña" required id="password"></div>
                <div class="form-group"><button class="btn btn-primary btn-block" type="submit">Log In</button></div>
            </form>
        </div>

 
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/login.js" type="text/javascript"></script>
         
    </body>

</html>
<script>
document.getElementById("loginP").style.color = "white";
</script>
    