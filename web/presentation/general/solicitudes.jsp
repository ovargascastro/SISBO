<%-- 
    Document   : solicitudes
    Created on : 27/02/2020, 05:56:29 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Existencias</title>
    </head>
    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Solicitudes de Artículos</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="catalogos"></div>
        <div class="card" id="tablaCatalogos" style="background-color: rgb(255,255,255);">
            <div class="card-body" style="padding-left: 5px;padding-right: 5px;">
                <div class="container" id="contenedorTabla">
                    <div class="row">
                        <div class="col text-center">
                            <h2>Pendientes</h2>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <th>Número de Solicitud</th>
                                    <th>Estado</th>
                                    <th>Artículos</th>
                                    </thead>
                                    <tbody id="listaPendientes">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col text-center">
                            <h2>Lista</h2>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Número de Solicitud</th>
                                            <th>Estado</th>
                                            <th>Artículos</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listado">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>