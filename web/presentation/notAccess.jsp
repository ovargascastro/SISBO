<%-- 
    Document   : notAccess
    Created on : 17/02/2020, 07:43:17 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Error</title>
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
        <link rel="stylesheet" href="assets/css/styles.css">

        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
    </head>

<body style="background-color: rgb(255,255,255);">
      <%@ include file="/presentation/header.jsp" %>

    <div id="titulo">
        <div class="jumbotron">
            <h1 style="font-family: Adamina, serif;">Recurso no disponible</h1>
            <p></p>
            <p></p>
        </div>
    </div>
    <div class="card" id="cuerpoListaOrdenes">
        <div class="card-body">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <form>
                            <div>
                                <div class="form-row">
                                    <div class="col">
                                        <div class="jumbotron text-center d-md-flex" style="background-color: rgb(0,123,255);">
                                            <h1 style="color: rgb(255,255,255);font-family: Adamina, serif;opacity: 1;filter: blur(0px) invert(0%);">Por favor inicie sesión para acceder a la página solicitada...</h1>
                                            <p></p>
                                            <p></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col"></div>
                                    <div class="col"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row"></div>
            </div>
        </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>