<%-- 
    Document   : codSicop
    Created on : 05/02/2020, 03:20:40 PM
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
        <title>Catálogo SICOP</title>
    </head>
    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Catálogo de SICOP</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="catalogos">
            <div class="card-body">
                <div class="container" id="contenedorCatalogos">
                    <div class="row">
                        <div class="col text-center">
                            <h4 class="text-center">Buscar</h4>
                            <form id="buscarSicop" action="javascript:buscarSicopFiltro()">
                                <div class="form-row">
                                    <div class="col text-center"><input class="form-control" type="text" id="filtro" onkeyup="myFunction()"></div>
                                </div>
                            </form>
                        </div>
                        <div class="col">
                            <h4>Agregar Registro</h4>
 
                            <button class="btn btn-primary" type="button" onclick="modalAgregaSicop()">Registrar</button></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card" id="tablaCatalogos" style="background-color: rgb(255,255,255);">
            <div class="card-body">
                <h4 class="card-title">Catálogo de SICOP</h4>
                <div class="container" id="contenedorTabla">
                    <div class="row">
                        <div class="table-responsive " style="max-height: 350px; overflow: auto">
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table" id="myTable">
                                        <thead>
                                            <tr>
                                                <th>Código Clasificación</th>
                                                <th>Código Identificación</th>
                                                <th>Descripción</th>
                                                <th>Editar</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listado"> </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" tabindex="-1" id="modalEliminar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Eliminar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Desea eliminar el registro seleccionado?</p>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button><button class="btn btn-primary bg-danger" type="button">Eliminar</button></div>
                </div>
            </div>
        </div>

        <form id="actualizaSicop" action="javascript:actualizarSicop()">
            <div class="modal fade" role="dialog" tabindex="-1" id="modalEditarSicop">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Edición</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                        <div class="modal-body">
                            <div class="container">
                                <form>
                                    <div class="form-row">
                                        <div class="col"><label>Código Clasificación</label>
                                            <input class="form-control" type="text" id="codClas" required></div>
                                        <div class="col"><label>Código Identificación</label>
                                            <input class="form-control" type="text" id="codId" required></div>
                                        <div class="col"><label>Descripción<br><br></label>
                                            <input class="form-control" type="text" id="desc" required></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>
                            <button class="btn btn-primary" type="submit">Guardar</button></div>
                    </div>
                </div>
            </div>
        </form>



        <form id="agregaSicop" action="javascript:agregarSicop()">
            <div class="modal fade" role="dialog" tabindex="-1" id="modalAgregarSicop">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Agregar Registro</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                        <div class="modal-body">
                            <div class="container">
                                <form>
                                    <div class="form-row">
                                        <div class="col"><label>Código Clasificación</label>
                                            <input class="form-control" type="text" id="codClas2" required></div>
                                        <div class="col"><label>Código Identificación</label>
                                            <input class="form-control" type="text" id="codId2" required></div>
                                        <div class="col"><label>Descripción<br><br></label>
                                            <input class="form-control" type="text" id="desc2" required></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button>
                            <button class="btn btn-primary" type="submit">Agregar</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>



        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/sicop.js" type="text/javascript"></script>
    </body>

</html>
<script>
function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("17") && !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
     document.getElementById("CatalogosMenu").style.color = "white";
</script>