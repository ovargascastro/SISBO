<%-- 
    Document   : index
    Created on : 15-sep-2019, 21:51:05
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Bienvenido al Sistema de Bodega</h1>
            </div>
        </div>
        <a href="presentation/proveeduria/ordenDeCompra.jsp">Orden de Compra</a>
        <br>
        <a href="presentation/proveeduria/listarOrdenes.jsp">Listado Ordenes de Compra</a>
        <br>
        <a href="presentation/proveeduria/asignarCodContable.jsp">Codigo Contable</a>
        <br>
        <a href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Articulos Nuevos</a>
        <br>
        <a href="presentation/catalogos/administracionCatalogos.jsp">Administracion de Catalogos</a>
    </body> 
</html>
