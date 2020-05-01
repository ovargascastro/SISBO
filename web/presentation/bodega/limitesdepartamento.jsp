<%-- 
    Document   : limitesdepartamento
    Created on : Mar 18, 2020, 9:51:50 AM
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Limites</title>
    </head>
    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Mantenimiento de Limites por departamento</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="catalogos">
            <div class="card-body">
                <div class="container" id="contenedorCatalogos">
                    <div class="row" aling="center">
                        <div class="col text-center">
                            <h4 class="text-center">Buscar</h4>
                            <form id="buscarSicop" action="javascript:getLimites()">
                               <div class="form-row">
                                        <div class="col">
                                            <label>Departamento</label>
                                            <select class="form-control" id="SelectDptos">
                                                 <option value="0">Sin Departamento</option>
                                            </select>
                                            <label>Artículo</label>
                                            <select class="form-control" id="selectSicop">
                                                <option value="0">Sin Artículo</option>
                                            </select>
                                        </div>
                                        <div class="col text-center">
                                            <button class="btn btn-primary" id="btnBuscarExist" type="submit">Buscar</button>
                                        </div>
                                    </div>
                            </form>
                        </div>
                        <div class="col">
                            <h4>Agregar Limite</h4>
                            <button class="btn btn-primary" type="button" onclick="modalAgregaBodega()">Registrar</button></div>
                        </div>
                </div>
            </div>
        </div>
        
        <div class="card" id="tablaCatalogos" style="background-color: rgb(255,255,255);">
            <div class="card-body">
                <h4 class="card-title">Limites</h4>
                <div class="container" id="contenedorTabla">
                    <div class="row">
                        <div class="table-responsive " style="max-height: 350px; overflow: auto">
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table" id="myTable">
                                        <thead>
                                            <tr>
                                                <th>Departamento</th>
                                                <th>Artículo</th>
                                                <th>Límite</th>
                                                <th>Editar</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listado">
                                        </tbody>
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
                        <h4 class="modal-title">Eliminar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <p>Desea eliminar el registro seleccionado?</p>
                        <div class="form-row">
                                        <div class="col">    
                                            <input class="form-control" type="text" id="DeleteDepa" hidden="true"  required>
                                        </div>
                                        <div class="col">
                                            <input class="form-control" type="text" id="DeleteArti" hidden="true" required>
                                        </div>
                                        <div class="col">
                                             <input type="text" class="form-control" id="DeleteLimi" hidden="true" >
                                        </div>
                                        
                                    </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button>
                        <button class="btn btn-primary bg-danger" type="button" onclick="javascript:deleteLimite()">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>

        <form id="actualizaSicop" action="javascript:actualizarLimite()">
            <div class="modal fade" role="dialog" tabindex="-1" id="modalEditarLimi">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Edición</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container">
                                    <div class="form-row">
                                        <div class="col">
                                            <label>Departamento</label>
                                            <input class="form-control" type="text" id="EditDepa" hidden="true" required>
                                            <input class="form-control" type="text" id="EditDepa2" disabled="true"  required>
                                        </div>
                                        <div class="col">
                                            <label>Artículo</label>
                                            <input class="form-control" type="text" id="EditArti" hidden="true"  required>
                                            <input class="form-control" type="text" id="EditArti2" disabled="true"  required>
                                        </div>
                                        <div class="col">
                                             <label>Límite</label>
                                             <input type="text" class="form-control" id="EditLimi">
                                        </div>
                                        
                                    </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>
                            <button class="btn btn-primary" type="submit">Guardar</button></div>
                    </div>
                </div>
            </div>
        </form>



        <form id="agregaBodega" action="javascript:agregarLimite()">
            <div class="modal fade" role="dialog" tabindex="-1" id="modalAgregarBodega">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Agregar Registro</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container">
                                    <div class="form-row">
                                        <div class="col">
                                             <label>Departamento</label>
                                            <select class="form-control" id="SelectDptosAdd">
                                            </select>
                                        </div>
                                        <div class="col">
                                             <label>Artículo</label>
                                             <select class="form-control" id="SelectSicopAdd">
                                            </select>
                                        </div>
                                        <div class="col">
                                             <label>Límite</label>
                                             <input type="text" class="form-control" id="LimiteAdd">
                                        </div>
                                    </div>
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
        <script src="assets/js/limitesdepartamento.js" type="text/javascript"></script>
        
        
        
    </body>

</html>

<script>
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
    document.getElementById("bodegasMenu").style.color = "white";
</script>