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
        <title>Mis Solicitudes</title>
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
                            <div class="table-responsive " style="max-height: 350px; overflow: auto">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <th>Número de Solicitud</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Artículos</th>
                                    </thead>
                                    <tbody id="listaPendientes">
                                    </tbody>
                                </table>
                            </div>
                            </div>
                        </div>
                        <div class="col text-center">
                            <h2>Lista</h2>
                            <div class="table-responsive " style="max-height: 350px; overflow: auto">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Número de Solicitud</th>
                                            <th>Fecha</th>
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
        </div>

               <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="listaArtxSol">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Listado de Artículos</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <div class="row">
                                <div class="col">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Artículo<br><br></th>
                                                    <th>Cantidad<br><br></th>
                                                </tr>
                                            </thead>
                                            <tbody id="listaArticulosSolicitudPendientes">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button></div>
                </div>
            </div>
        </div>
        
        
        
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/misSolicitudes.js" type="text/javascript"></script>

    </body>
</html>
<script>
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null ) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
    document.getElementById("solicitudArtMenu").style.color = "white";
</script>