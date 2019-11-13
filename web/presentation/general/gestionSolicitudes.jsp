<%-- 
    Document   : gestionSolicitudes
    Created on : Oct 13, 2019, 1:02:15 AM
    Author     : boris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" type="text/css" href="assets/css/Imprimir.css" media="print" />
        <title>Gestión de Solicitudes</title>

    </head>

    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>

        <div id="titulo">
            <div class="jumbotron">
                <h1> Gestión de Solicitudes de Requisición de Articulos</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="cuerpoListaOrdenes">
            <div class="card-body">
                <h4 class="text-center card-title">Listado  de Solicitudes de Requisición de Articulos</h4>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form>
                                <div><label>Buscar Número de Solicitud</label>
                                    <div class="form-row">
                                        <div class="col"><input class="form-control" type="text" id="filtro"></div>
                                        <div class="col"><button class="btn btn-primary" type="button" onclick="javascript:buscarSolicitudxAprobar()">Buscar</button></div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <br>
                                            <img src="assets/img/information.png" onclick="$('#information').modal('show');"> 
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col text-center" id="tablaSolicitudes">
                            <div class="table-responsive " style="max-height: 350px; overflow: auto">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Número<br>de solicitud</th>
                                            <th class="text-center">Fecha</th>
                                            <th class="text-center">Unidad usuaria</th>
                                            <th class="text-center">Estado</th>
                                            <th class="text-center">Artículos</th>
                                            <th class="text-center">Editar Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listSolArt">
                                    </tbody>
                                </table>
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
                                            <tbody id="listaArticulosSolicitud">

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


        <div role="dialog" tabindex="-1" class="modal fade" id="modalAprobar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Aprobación de Solicitudes</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Si lo necesita solicite el visto bueno necesario</p>

                        <button class="btn btn-light" type="button" onclick="javascript:actualizarEstadoJefe()">Visto Bueno Jefe</button> 
                        <button class="btn btn-light" type="button" onclick="javascript:actualizarEstadoTI()">Visto Bueno TI</button>
                        <p><br>¿Desea Aprobar la solicitud seleccionada?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary bg-danger" type="button" data-dismiss="modal" onclick="javascript:abrirModalRechazar()">Rechazar</button>  
                        <button class="btn btn-success" type="button" onclick="javascript:actualizarExistenciaEstado()">Aprobar</button></div>
                </div>
            </div>
        </div>

        <div role="dialog" tabindex="-1" class="modal fade" id="modalAprobarJEFE">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Aprobación de Solicitudes TI</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Si lo necesita solicite el visto bueno del departamento de TI</p>

                        <button class="btn btn-light" type="button" onclick="javascript:actualizarEstadoTI()">Visto Bueno TI</button>

                        <p><br>¿Desea Aprobar la solicitud seleccionada?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary bg-danger" type="button" data-dismiss="modal" onclick="javascript:abrirModalRechazar()">Rechazar</button>  
                        <button class="btn btn-success" type="button" onclick="javascript:actualizarExistenciaEstado()">Aprobar</button></div>
                </div>
            </div>
        </div>

        <div role="dialog" tabindex="-1" class="modal fade" id="modalAprobarTI">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Aprobación de Solicitudes TI</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Si lo necesita solicite el visto bueno del Jefe de departamento</p>

                        <button class="btn btn-light" type="button" onclick="javascript:actualizarEstadoJefe()">Visto Bueno Jefe</button> 

                        <p><br>¿Desea Aprobar la solicitud seleccionada?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary bg-danger" type="button" data-dismiss="modal" onclick="javascript:abrirModalRechazar()">Rechazar</button>  
                        <button class="btn btn-success" type="button" onclick="javascript:actualizarExistenciaEstado()">Aprobar</button></div>
                </div>
            </div>
        </div>

        <div role="dialog" tabindex="-1" class="modal fade" id="modalAprobarAmbas">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Aprobación de Solicitudes</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Esta solicitud ya cuenta con los vistos buenos del Jefe de departamento y del departamento de TI</p>

                        <p><br>¿Desea Aprobar la solicitud seleccionada?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary bg-danger" type="button" data-dismiss="modal" onclick="javascript:abrirModalRechazar()">Rechazar</button>  
                        <button class="btn btn-success" type="button" onclick="javascript:actualizarExistenciaEstado()">Aprobar</button></div>
                </div>
            </div>
        </div>

        <div role="dialog" tabindex="-1" class="modal fade" id="modalPendiente">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Aprobación de Solicitudes</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Esta solicitud esta a la espera del  visto bueno solicitado</p>


                    </div>
                    <div class="modal-footer">

                        <button class="btn btn-success" type="button" onclick="javascript:cerrarPendiente()">Aceptar</button></div>
                </div>
            </div>
        </div>


        <div role="dialog" tabindex="-1" class="modal fade" id="Rechazar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Rechazar la Solitudes</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Motivo del rechazo de la solicitud</p>
                        <div class="col"><input class="form-control" type="text" placeholder="Digite el motivo"  id="motivo"></div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>  <button class="btn btn-primary bg-danger" type="button" onclick="javascript:actualizarEstadoRechazo()">Rechazar</button></div>
                </div>
            </div>
        </div>

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

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/solicitudArticulo.js" type="text/javascript"></script>
    </body>
    <script>
                        document.getElementById("solicitudArtMenu").style.color = "white";
                        function listSoliArt(personas) {
                            var listado = $("#listSolArt");
                            listado.html("");
                            personas.forEach((p) => {
                                filaSolArt(listado, p);
                            });
                        }

                        function filaSolArt(listado, objeto) {
                            var tr = $("<tr />");
                            tr.html(
                                    "<td>" + objeto.solArtiIdPk + "</td>"
                                    + "<td>" + formatDate(objeto.solArtiFechSoli) + "</td>"
                                    + "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>"
                                    + "<td>" + objeto.solArtiEsta + "</td>"
                                    + "<td><img src='assets/img/delivery-cart.png' onclick='articulosXSolicitud(\"" + objeto.solArtiIdPk + "\");'></td>"
                                    + "<td><img src='assets/img/edit.png' onclick='abrirModalAprobar(\"" + objeto.solArtiIdPk + "\");'></td>"
                                    );
                            listado.append(tr);

                        }

                        //onclick='articulosXSolicitud(\"" + objeto.solArtiIdPk + "\");'


                        function listaArticulosxSol(personas) {
                            var listado = $("#listaArticulosSolicitud");
                            listado.html("");
                            personas.forEach((p) => {
                                filaArticulos(listado, p);
                            });
                        }



                        function filaArticulos(listado, objeto) {
                            var tr = $("<tr />");
                            tr.html(
                                    "<td>" + objeto.sboTbArticulo.artDesc + "</td>"
                                    + "<td>" + objeto.solArtiCant + "</td>");

                            listado.append(tr);

                        }
    </script>
</html>
