
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
    </head>
    <body>
        <nav class="navbar navbar-dark navbar-expand-sm bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp"> <img src="assets/img/Escudo.png" width="70" height="60"> </a>
                <a class="navbar-brand" id="font"><img src="assets/img/increase.png" onclick="javascript:increaseFontSizeBy1px()"></a>
                <a class="navbar-brand" id="font"><img src="assets/img/decrease.png" onclick="javascript:decreaseFontSizeBy1px()"></a>
                <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="nav navbar-nav ml-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp" id="index">Inicio</a>
                           
                        </li>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ordenCompraMenu" data-toggle="dropdown">
                                Orden de Compra
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/proveeduria/ordenDeCompra.jsp">Ingresar Orden de Compra</a>
                                <a class="dropdown-item" href="presentation/proveeduria/listarOrdenes.jsp">Listado Órdenes de Compra</a>
                                <a class="dropdown-item" href="presentation/proveeduria/asignarCodContable.jsp">Asignar Código Contable</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ArticulosMenu" data-toggle="dropdown">
                                Artículos
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Artículos Nuevos</a>
                                <a class="dropdown-item" href="presentation/bodega/existencias.jsp">Existencias</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="solicitudArtMenu" data-toggle="dropdown">
                                Solicitud de artículos
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/general/solicitudArticulos.jsp">Solicitud nueva</a>
                                <a class="dropdown-item" href="presentation/general/listarSolicitudesArticulos.jsp">Listado de solicitudes</a>
                                <a class="dropdown-item" href="presentation/general/gestionSolicitudes.jsp">Gestión de Solitudes</a>
                                <a class="dropdown-item" href="presentation/general/vistoBuenoJefe.jsp">Visto bueno de Jefe</a>
                                <a class="dropdown-item" href="presentation/general/vistoBuenoTI.jsp">Visto bueno de TI</a>                            </div>
                        </div>
                        <li class="nav-item"><a class="nav-link" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/funciones.js" type="text/javascript"></script>
    </body>
</html>

<script>
    $(document).ready(function () {
        $(".dropdown-toggle").dropdown();
    });
</script>

