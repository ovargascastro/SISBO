<%-- 
    Document   : administracion
    Created on : 01/06/2020, 08:16:43 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <title>Administración</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">

    </head>
<body style="background-color: rgb(255,255,255);" onload="logged();">

    <%@ include file="/presentation/header.jsp" %>
    
    
    <div id="titulo">
        <div class="jumbotron">
            <h1>Admistración</h1>
            <p></p>
            <p></p>
        </div>
    </div>
    <div class="features-boxed">
        <div class="container">
            <div class="intro">
                <h2 class="text-center">Módulos SISBO</h2>
                <p class="text-center"></p>
            </div>
            <div class="row justify-content-center features">
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box">
                        <h3 class="name">Proveeduría</h3>
                        <div class="row">
                            <div class="col">
                                
                                <button class="btn btn-primary" type="button" onclick="location.href='presentation/proveeduria/ordenDeCompra.jsp';">Orden de Compra</button>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/proveeduria/listarOrdenes.jsp';">Listado Ordenes de Compra</button>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/catalogos/administracionCatalogos.jsp';">Catálogos</button>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/catalogos/codSicop.jsp';">Catálogo SICOP</button>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box">
                        <h3 class="name">Bodega</h3>
                        <div class="row">
                            <div class="col text-center"><button class="btn btn-primary" type="button" onclick="location.href='presentation/bodega/ingresoArticulosNuevos.jsp';">Ingreso de Artículos por Orden de Compra</button>
                                <hr><button class="btn btn-primary" type="button" onclick="location.href='presentation/bodega/ingresoArticulos.jsp';">Ingreso de Artículos por Otros Medios</button>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-center"><button class="btn btn-primary" type="button" onclick="location.href='presentation/general/gestionSolicitudes.jsp';">Gestión de Solicitudes</button>
                                <hr><button class="btn btn-primary" type="button" onclick="location.href='presentation/general/listarSolicitudesArticulos.jsp';">Listado de Solicitudes</button>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/bodega/limitesdepartamento.jsp';">Límites</button>
                                <hr><button class="btn btn-primary" type="button" onclick="location.href='presentation/bodega/tomaFisica.jsp';">Toma Física</button></div>
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/bodega/bodegas.jsp';">Bodegas</button>
                                <hr><button class="btn btn-primary" type="button" onclick="location.href='presentation/bodega/existencias.jsp';">Existencias</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="box">
                        <h3 class="name">Contabilidad</h3>
                        <div class="row">
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/proveeduria/asignarCodContable.jsp';">Asignar Códigos Contables en Orden de Compra</button>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col"><button class="btn btn-primary" type="button" onclick="location.href='presentation/proveeduria/asignarCodContArtNuevo.jsp';">Asignar Códigos Contables Otros Artículos</button>
                                <hr>
                            </div>
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

<script>
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null ) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
   

</script>