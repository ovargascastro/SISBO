<%-- 
    Document   : usuarios
    Created on : 04/05/2020, 12:51:22 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <title>Usuarios</title>
    </head>
    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Gestión de usuarios</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="catalogos">
            <div class="card-body">
                <div class="container" id="contenedorCatalogos">
                    <div class="row" aling="center">
                        <div class="col text-center">
                            <h4 class="text-center">Buscar usuario</h4>
                            <form id="buscarSicop" action="javascript:buscarSicopFiltro()">
                                <div class="form-row">
                                    <div class="col text-center">
                                        <input class="form-control" type="text" id="filtro" onkeyup="myFunction()" placeholder="Búsqueda por cédula"></div>
                                </div>
                            </form>
                        </div>
                        <div class="col">
                            <h4>Agregar Usuario</h4>
                            <button class="btn btn-primary" type="button" onclick="abrirModalAgregar()">Agregar</button></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card" id="tablaCatalogos" style="background-color: rgb(255,255,255);">
            <div class="card-body">
                <h4 class="card-title">Usuarios</h4>
                <div class="container" id="contenedorTabla">
                    <div class="row">
                        <div class="table-responsive " style="max-height: 350px; overflow: auto">
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table" id="myTable">
                                        <thead>
                                            <tr>
                                                <th>Cédula</th>
                                                <th>Nombre</th>
                                                <th>Apellido 1</th>
                                                <th>Apellido 2</th>
                                                <th>Departamento</th>
                                                <th>Editar</th>
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

        <form action="javascript:agregarUsuario()" id="myForm">
            <div class="modal fade" role="dialog" tabindex="-1" id="agregarUsuarioModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Agrear Usuario</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="form-row">
                                <div class="col">
                                    <label>Cédula</label>
                                    <input class="form-control" type="text" id="cedUsuario" required placeholder="Cédula">
                                    <label>Apellido 1</label>
                                    <input class="form-control" type="text" id="ap1Usuario" required placeholder="Apellido 1">
                                    <label>Departamento</label>
                                    <select id="selectDptoPicker" class="selectpicker form-control " 
                                            data-live-search="true" data-size="15" required="true">
                                        <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                    </select>

                                    <style>
                                        div.dropdown-menu.open { width: 100%; }
                                        ul.dropdown-menu.inner>li>a { white-space: initial; }
                                    </style>
                                    <label>Contraseña</label>
                                    <input class="form-control" type="password" id="passUsuario" required>

                                </div>
                                <div class="col">
                                    <label>Nombre</label>
                                    <input class="form-control" type="text" id="nombUsuario" required placeholder="Nombre">
                                    <label>Apellido 2</label>
                                    <input class="form-control" type="text" id="ap2Usuario" required placeholder="Apellido 2">
                                    <label>Rol por Permiso</label>
                                    <select id="selectRoles" class="form-control" required="true">
                                            
                                        <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                    </select>
                                    <label>Es jefe ?</label>
                                     <br>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" id="customRadioInline1" id="jefe" value="1" name="customRadioInline1" class="custom-control-input">
                                        <label class="custom-control-label" for="customRadioInline1">Sí</label>
                                    </div>  
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" id="customRadioInline2" value="0" id="nojefe" name="customRadioInline1" class="custom-control-input">
                                        <label class="custom-control-label" for="customRadioInline2">No</label>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Agregar</button></div>
                    </div>
                </div>
            </div>
        </form>


        <form action="javascript:actualizarUsuario()" id="myForm2">
            <div class="modal fade" role="dialog" tabindex="-1" id="editarUsuarioModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Usuario</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="form-row">
                                <div class="col">
                                    <label>Cédula</label>
                                    <input class="form-control" type="text" id="cedUsuarioEd" required placeholder="Cédula" readonly="true">
                                    <label>Apellido 1</label>
                                    <input class="form-control" type="text" id="ap1UsuarioEd" required placeholder="Apellido 1">
                                    <label>Departamento</label>
                                    <select id="selectDptoPickerEd" class="selectpicker form-control" 
                                            data-live-search="true" data-size="15" required>
                                        <option values="0" disabled = "true" >Seleccione una opcion</option>
                                    </select>
                                    <style>
                                        div.dropdown-menu.open { width: 100%; }
                                        ul.dropdown-menu.inner>li>a { white-space: initial; }
                                    </style>

                                    <label>Contraseña</label>
                                    <input class="form-control" type="password" id="passUsuarioEd">
                                </div>
                                <div class="col">
                                    <label>Nombre</label>
                                    <input class="form-control" type="text" id="nombUsuarioEd" required placeholder="Nombre">
                                    <label>Apellido 2</label>
                                    <input class="form-control" type="text" id="ap2UsuarioEd" required placeholder="Apellido 2">
                                    <label>Es jefe ?</label>
                                    <br>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" id="customRadioInline12" id="jefeEd" value="1" name="customRadioInline2" class="custom-control-input">
                                        <label class="custom-control-label" for="customRadioInline12">Sí</label>
                                    </div>
                                    <br>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" id="customRadioInline22" value="0" id="nojefeEd" name="customRadioInline2" class="custom-control-input">
                                        <label class="custom-control-label" for="customRadioInline22">No</label>
                                    </div>
                                    <textarea class="form-control" id="textCont" readonly="true"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Actualizar</button></div>
                    </div>
                </div>
            </div>
        </form>


        <script src="assets/js/jquery.min.js"></script>

        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <script src="assets/js/Usuarios.js" type="text/javascript"></script>


    </body>
</html>
<script>
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null ) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
    document.getElementById("usuariosMenu").style.color = "white";
    

</script>