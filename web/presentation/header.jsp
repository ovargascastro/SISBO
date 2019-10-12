

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
            
            <div class="container-fluid"><a class="navbar-brand" href="index.jsp"> <img src="assets/img/Escudo.png" width="70" height="60"> </a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="index.jsp">Inicio</a></li>
                        
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/proveeduria/ordenDeCompra.jsp">Orden de Compra</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/proveeduria/listarOrdenes.jsp">Listado Órdenes de Compra</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/proveeduria/asignarCodContable.jsp">Código Contable</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Artículos Nuevos</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a></li>
                         <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/bodega/existencias.jsp">Existencias</a></li>
                         <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/general/solicitudArticulos.jsp">Solicitud de Articulos</a></li>
                         <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/general/listarSolicitudesArticulos.jsp">Listado Solicitud de Articulos</a></li>
                        <%--
                        <li class="nav-item dropdown" >
                            <a class="nav-link dropdown-toggle active"  id="ordenDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Órdenes de compra
                            </a>    
                            <div class="dropdown-menu" aria-labelledby="ordenDropdown">
                                <a class="dropdown-item" href="presentation/proveeduria/ordenDeCompra.jsp">Nueva Orden de Compra</a>
                                
                                <a class="dropdown-item" href="presentation/proveeduria/listarOrdenes.jsp">Listado</a>
                                
                                <a class="dropdown-item" href="presentation/proveeduria/asignarCodContable.jsp">Asignar Código Contable</a>
                            </div>    
                        </li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Artículos Nuevos</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link active" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a></li>
                        --%>
                    </ul>
                </div>
            </div>
        </nav>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
