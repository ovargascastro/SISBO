
<%@page import="logic.AbaaTbPersona"%>
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
        <% AbaaTbPersona logged = (AbaaTbPersona) session.getAttribute("logged");%>

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
                        <% if (logged == null) {%>
                        <li class="nav-item">
                            <a class="nav-link" href="presentation/login.jsp" id="loginP">LogIn</a>
                        </li>
                        <% } else {%>
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp" id="index">Inicio</a>
                        </li>
                        <!--CONTABILIDAD-->
                        <% if ("8".equals(logged.getDepartamento().getDeptoIdPk()) && logged.getPers_es_jefe() == 1) {%>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ordenCompraMenu" data-toggle="dropdown"> Orden de Compra</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/proveeduria/asignarCodContable.jsp">Asignar Código Contable</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="solicitudArtMenu" data-toggle="dropdown">
                                Solicitud de artículos
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/general/solicitudArticulos.jsp">Solicitud nueva</a>
                                <a class="dropdown-item" href="presentation/general/vistoBuenoJefe.jsp">Visto bueno de Jefe</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="CatalogosMenu" data-toggle="dropdown">
                                Catálogos
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a>
                            </div>
                        </div>
                        <%}%>
                        <!--BODEGA-->
                        <% if ("5".equals(logged.getDepartamento().getDeptoIdPk()) && logged.getPers_es_jefe() == 1) {%>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ordenCompraMenu" data-toggle="dropdown"> Orden de Compra </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/proveeduria/listarOrdenes.jsp">Listado Órdenes de Compra</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ArticulosMenu" data-toggle="dropdown"> Artículos </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/bodega/ingresoArticulosNuevos.jsp">Ingreso de Artículos por OC</a>
                                <a class="dropdown-item" href="presentation/bodega/ingresoArticulos.jsp">Ingreso de Artículos</a>
                                <a class="dropdown-item" href="presentation/bodega/existencias.jsp">Existencias</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="solicitudArtMenu" data-toggle="dropdown"> Solicitud de artículos </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/general/solicitudArticulos.jsp">Solicitud nueva</a>
                                <a class="dropdown-item" href="presentation/general/listarSolicitudesArticulos.jsp">Listado de solicitudes</a>
                                <a class="dropdown-item" href="presentation/general/gestionSolicitudes.jsp">Gestión de Solitudes</a>
                                <a class="dropdown-item" href="presentation/general/vistoBuenoJefe.jsp">Visto bueno de Jefe</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="CatalogosMenu" data-toggle="dropdown"> Catálogos </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a>
                                <a class="dropdown-item" href="presentation/proveeduria/proveedores.jsp">Catálogo de Proveedores</a>
                                <a class="dropdown-item" href="presentation/catalogos/codSicop.jsp">Catálogo de SICOP</a>
                            </div>
                        </div>
                        <%}%>
                        <!-- PROVEDURIA -->
                        <!-- Secretaria -->
                        <% if ("17".equals(logged.getDepartamento().getDeptoIdPk()) && logged.getPers_es_jefe() == 0) {%>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ordenCompraMenu" data-toggle="dropdown"> Orden de Compra </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/proveeduria/ordenDeCompra.jsp">Ingresar Orden de Compra</a>
                                <a class="dropdown-item" href="presentation/proveeduria/listarOrdenes.jsp">Listado Órdenes de Compra</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="solicitudArtMenu" data-toggle="dropdown"> Solicitud de artículos </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/general/solicitudArticulos.jsp">Solicitud nueva</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="CatalogosMenu" data-toggle="dropdown">
                                Catálogos
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a>
                                <a class="dropdown-item" href="presentation/proveeduria/proveedores.jsp">Catálogo de Proveedores</a>
                                <a class="dropdown-item" href="presentation/catalogos/codSicop.jsp">Catálogo de SICOP</a>
                            </div>
                        </div>
                        <%}%>
                        
                         <!-- JefeTI -->
                        <% if ("6".equals(logged.getDepartamento().getDeptoIdPk()) && logged.getPers_es_jefe() == 1) {%>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="solicitudArtMenu" data-toggle="dropdown"> Solicitud de artículos </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/general/solicitudArticulos.jsp">Solicitud nueva</a>
                                <a class="dropdown-item" href="presentation/general/vistoBuenoTI.jsp">Visto bueno de TI</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="CatalogosMenu" data-toggle="dropdown">
                                Catálogos
                            </a>
                        </div>
                        <%}%>
                        <!--PROVEDURIA-->
                        <!-- Jefa -->
                        <% if ("17".equals(logged.getDepartamento().getDeptoIdPk()) && logged.getPers_es_jefe() == 1) {%>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="ordenCompraMenu" data-toggle="dropdown"> Orden de Compra </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/proveeduria/ordenDeCompra.jsp">Ingresar Orden de Compra</a>
                                <a class="dropdown-item" href="presentation/proveeduria/listarOrdenes.jsp">Listado Órdenes de Compra</a>
                            </div>
                        </div>
                        <!-- -->
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="solicitudArtMenu" data-toggle="dropdown"> Solicitud de artículos </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/general/solicitudArticulos.jsp">Solicitud nueva</a>
                                <a class="dropdown-item" href="presentation/general/vistoBuenoJefe.jsp">Visto bueno de Jefe</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="CatalogosMenu" data-toggle="dropdown">
                                Catálogos
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="presentation/catalogos/administracionCatalogos.jsp">Administración de Catálogos</a>
                                <a class="dropdown-item" href="presentation/proveeduria/proveedores.jsp">Catálogo de Proveedores</a>
                                <a class="dropdown-item" href="presentation/catalogos/codSicop.jsp">Catálogo de SICOP</a>
                            </div>
                        </div>
                        <%}%>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="UsuarioActual" data-toggle="dropdown">
                                <%= logged.getPersNomb() + " " + logged.getPersApe1()%>
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="SISBO/logout/cerrarsesion">Cerrar Sesión</a>
                            </div>
                        </div>

                        <% }%>

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

