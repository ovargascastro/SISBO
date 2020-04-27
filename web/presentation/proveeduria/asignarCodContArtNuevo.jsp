<%-- 
    Document   : asignarCodContable
    Created on : 25/09/2019, 12:06:39 AM
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

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

        <!-- (Optional) Latest compiled and minified JavaScript translation files -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/i18n/defaults-*.min.js"></script>
        <title>Asignar Codigo Contable</title>
    </head>

    <body style="background-color: rgb(255,255,255);" onload="javascript:articulos()">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Asignar Códigos Contables</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="cuerpoListaOrdenes">
            <div class="card-body">
                <h4 class="text-center card-title">Listado de Artículos</h4>
                <div class="container">
                    <div class="row">
                        <div class="col text-center" id="tablaArticulos">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Artículo</th>
                                            <th class="text-center">Sub-Familia</th>
                                            <th class="text-center">Sub-Familia<br>Descripción<br></th>
                                            <th class="text-center">Asignar Código Contable</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listaArticulos">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" role="dialog" tabindex="-1" id="informacionArt">
            <div class="modal-dialog" role="document">
                <form id="actualizaArticulo" action="javascript:actualizarArticulo()">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Informacion de Artículo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                        <div class="modal-body">

                            <div class="container">
                                <div class="form-row">
                                    <div class="col">

                                        <label>Artículo</label>
                                        <input id="ArticuloInfo" class="form-control" type="text" readonly placeholder="Artículo">
                                        <label>Descripción</label>
                                        <input id="DescripcionInfo" class="form-control" type="text" readonly placeholder="Descripcion">
                                        <label>Modelo</label>
                                        <input id="ModeloInfo" class="form-control" type="text" readonly placeholder="Modelo">
                                        <label>Marca</label>
                                        <input id="MarcaInfo" class="form-control" type="text" readonly placeholder="Marca">
                                    </div>                       
                                    <div class="col">
                                        <label>N° Orden de Compra</label>
                                        <input id="OrdenInfo" class="form-control" type="text" readonly placeholder="N° Orden de Compra">
                                        <label>SICOP</label>
                                        <input id="SicopInfo" class="form-control" type="text" readonly placeholder="SICOP">
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>
                        </div>
                </form>

            </div>
        </div>
    </div>

    <!--
    <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="asignarCodido">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <h4 class="modal-title">Asginar Código Contable</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <div class="row">
                                <div class="col">
                                    <div class="table-responsive">
                                        <select id="selectConta"
                                                class="selectpicker form-control" 
                                                data-live-search="true"  
                                                data-size="15"  
                                                required>
                                            <option values="0" selected disabled = "true">
                                                Seleccione una opcion
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Asignar Codigos</button></div>
                </form>
            </div>
        </div>
    </div>
    -->

    <!--
    <div class="modal fade" role="dialog" tabindex="-1" id="information">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Información/Ayuda</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <form>
                        <div class="container">
                            <div class="form-row">
                                <div class="col">
                                    <p class="font-italic">
                                        Digite un número de solicitud y haga clic en el botón Buscar.<br>
                                        De no digitar un número se listarán todas las solicitudes.
                                    </p>
                                </div>

                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button></div>
            </div>
        </div>
    </div>
    -->
    <style>
        div.dropdown-menu.open { width: 150%; }
        ul.dropdown-menu.inner>li>a { white-space: initial; }


    </style>

    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

    <script src="assets/js/articulos.js" type="text/javascript"></script>


</body>
<script>
    document.getElementById("codContableMenu").style.color = "white";

    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("8")) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }






</script>
</html>
