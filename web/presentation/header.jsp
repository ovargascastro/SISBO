<%-- 
    Document   : header
    Created on : 15-sep-2019, 21:49:09
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
        <nav class="navbar navbar-dark navbar-expand-md bg-primary">
            <div class="container-fluid"><a class="navbar-brand" href="index.jsp">Logo</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="index.jsp">Inicio</a></li>
                        
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/proveeduria/ordenDeCompra.jsp">Orden de Compra</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/proveeduria/listarOrdenes.jsp">Listado Ordenes de Compra</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/proveeduria/asignarCodContable.jsp">Codigo Contable</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Articulos Nuevos</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/catalogos/administracionCatalogos.jsp">Administracion de Catalogos</a></li>
                        <%--
                        <li class="nav-item dropdown" >
                            <a class="nav-link dropdown-toggle active" href="#" id="ordenDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Ordenes de compra
                            </a>    
                            <div class="dropdown-menu" aria-labelledby="ordenDropdown">
                                <a class="dropdown-item" href="presentation/proveeduria/ordenDeCompra.jsp">Nueva Ordend de Compra</a>
                                
                                <a class="dropdown-item" href="presentation/proveeduria/listarOrdenes.jsp">Listado</a>
                                
                                <a class="dropdown-item" href="presentation/proveeduria/asignarCodContable.jsp">Asignar Codigo Contable</a>
                            </div>    
                        </li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Articulos Nuevos</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/catalogos/administracionCatalogos.jsp">Administracion de Catalogos</a></li>
                        --%>
                    </ul>
                </div>
            </div>
        </nav>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
