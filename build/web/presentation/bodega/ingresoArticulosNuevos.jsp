<%-- 
    Document   : ingresoArticulosNuevos
    Created on : 15-sep-2019, 21:58:32
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Ingreso de Articulos Nuevos</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Ingresar Artículos Nuevos</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="cuerpoListaOrdenes">
            <div class="card-body">
                <h4 class="text-center card-title">Listado de Órdenes de Compra</h4>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form>
                                <div><label>Buscar Orden de Compra</label>
                                    <div class="form-row">
                                        <div class="col"><input class="form-control" type="text" placeholder="Codigo Orden de Compra" id="numeroOC" name="numeroOC"></div>
                                        <div class="col"><button class="btn btn-primary" type="button" onclick="buscarOrdenes()">Buscar</button></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col text-center" id="tablaOrdenes">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Número<br>de Orden</th>
                                            <th class="text-center">Fecha<br>de Solicitud</th>
                                            <th class="text-center">Estado</th>
                                            <th class="text-center">Artículos</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listadoOC">
                                        <!--<tr>
                                            <td>02210</td>
                                            <td>08/09/2019</td>
                                            <td>Pendiente</td>
                                            <td><img src="assets/img/delivery-cart.png" onclick="abrirModalListarArticulos()"></td>
                                        </tr>
                                        <tr>
                                            <td>02209</td>
                                            <td>07/09/2019</td>
                                            <td>Pariclamente<br>Procesada</td>
                                            <td><img src="assets/img/delivery-cart.png"></td>
                                        </tr>-->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" tabindex="-1" id="listaArticulos">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Listado de Articulos</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container text-center">
                            <div class="row">
                                <div class="col">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Articulo</th>
                                                    <th>Cantidad</th>
                                                    <th>Agregar</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Sillas</td>
                                                    <td>50</td>
                                                    <td><img src="assets/img/plus.png" onclick="abrirModalAgregarArticulos()"></td>
                                                </tr>
                                                <tr>
                                                    <td>Bombillos</td>
                                                    <td>25</td>
                                                    <td><img src="assets/img/plus.png"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button class="btn btn-primary" type="button">Save</button></div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" tabindex="-1" id="agregarArticulo">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Agregar Articulo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <form>
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Articulo</label><input id="articulo" class="form-control" type="text" readonly><label>Fecha de Ingreso</label><input class="form-control" type="date"><label>Cantidad a Ingresar</label>
                                        <input class="form-control" type="number"></div>
                                    <div class="col"><label>Descripcion</label><input class="form-control" type="text" placeholder="Descripcion"><label>Fecha de Vencimiento</label>
                                        <input
                                            class="form-control" type="date"><label>Informacion del Articulo</label>
                                        <div class="text-center"><img src="assets/img/info(1).png" onclick="abrirModalInfoArticulo()"></div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button class="btn btn-primary" type="button">Agregar</button></div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" tabindex="-1" id="modalInfoArt">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Informacion del Articulo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <form>
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Articulo</label><input class="form-control" type="text" id="articuloInfo" readonly><label>Sub-Familia</label><input class="form-control" type="text" id="subfamInfo" readonly></div>
                                    <div class="col"><label>Codigo Articulo</label><input class="form-control" type="text" id="codArtInfo" readonly><label>Familia</label><input class="form-control" type="text" id="famInfo" readonly></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" onclick="cerrarInfoArt()" >Cerrar</button></div>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>

<script>
                        function abrirModalListarArticulos() {
                            $('#listaArticulos').modal('show');
                        }

                        function abrirModalAgregarArticulos() {
                            $('#listaArticulos').modal('hide');
                            $('#articulo').val('Silla');
                            $('#agregarArticulo').modal('show');
                        }

                        function abrirModalInfoArticulo() {
                            $('#agregarArticulo').modal('hide');
                            $('#articuloInfo').val('Silla');
                            $('#subfamInfo').val('Equipo y mobiliario de oficina');
                            $('#codArtInfo').val('1234');
                            $('#famInfo').val('Bienes Duraderos');
                            $('#modalInfoArt').modal('show');
                        }

                        function cerrarInfoArt() {
                            $('#agregarArticulo').modal('show');
                            $('#modalInfoArt').modal('hide');
                        }

                        function buscarOrdenes() {
                            $.ajax({type: "GET",
                                url: "api/listadoOCArtNuevos?numeroOC=" + $("#numeroOC").val(),
                                success: listaOC
                            });
                        }

                        function listaOC(ordenes) {
                            var listado = $("#listadoOC");
                            listado.html("");
                            ordenes.forEach((p) => {
                                filaOC(listado, p);
                            });
                        }

                        function filaOC(listado, objeto) {
                            var tr = $("<tr />");
                            tr.html(
                                    "<td>" + objeto.ocIdPk + "</td>"
                                    + "<td>" + objeto.ocFecha + "</td>"
                                    + "<td>" + objeto.ocEsta + "</td>"
                                    + "<td><img class='small-img' src='assets/img/delivery-cart.png' onclick='abrirModalListarArticulos()'></td>");
                            listado.append(tr);
                        }



</script>
