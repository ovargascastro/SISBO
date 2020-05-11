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
        
        <title>Existencias</title>
    </head>

    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Artículos en Existencia</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="cuerpoListaOrdenes">
            <div class="card-body">
                <h4 class="text-center card-title">Existencias</h4>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form id="existencias" action="javascript:getExistencias()" >
                                <div>
                                    <label>Bodega</label>
                                    <div class="form-row">
                                        <div class="col">
                                            <select class="form-control" id="SelectBodegas">
                                            </select>
                                        </div>
                                        <div class="col"></div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <label>Departamento</label>
                                            <select class="form-control" id="SelectDptos">
                                            </select>
                                            <label>Artículo</label>
                                    <select id="selectSicop" class="selectpicker form-control" 
                                            data-live-search="true" data-size="15" required>
                                        <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                        <option value="all" >➤ Todos los artículos</option>
                                    </select>
                                    <style>
                                        div.dropdown-menu.open { width: 100%; }
                                        ul.dropdown-menu.inner>li>a { white-space: initial; }
                                    </style>
                                        </div>
                                        <div class="col text-center">
                                            <button class="btn btn-primary" id="btnBuscarExist" type="submit">Buscar</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <br>
                            <h2 id="nReg">Numero de registros : </h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col text-center" id="tablaOrdenes">
                            <div class="table-responsive " style="max-height: 350px; overflow: auto">
                                <div class="table-responsive">
                                    <table class="table" id="myTable">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Fecha Ingreso</th>
                                                <th class="text-center">Bodega</th>
                                                <th class="text-center">Departamento</th>
                                                <th class="text-center">Descripcion</th>
                                                <th class="text-center">Precio</th>
                                                <th class="text-center">Infomacion</th>
                                                <th class="text-center">Eliminar</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listadoExistencias">
                                        </tbody>
                                    </table>
                                </div>
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
        
                <div class="modal fade" role="dialog" tabindex="-1" id="modalEliminaExist">
             <form action="javascript:eliminarExistencia();">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Eliminar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Desea eliminar el registro seleccionado?</p>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button>
                        <button class="btn btn-primary bg-danger" type="submit">Eliminar</button></div>
                </div>
            </div>
                 </form>
        </div>




        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        
        <script src="assets/js/existencias.js" type="text/javascript"></script>
    </body>

</html>
<script>
    document.getElementById("ArticulosMenu").style.color = "white";
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
    
    $(document).ready(function () {
  $('#dtBasicExample').DataTable({
    "paging": false // false to disable pagination (or any other option)
  });
  $('.dataTables_length').addClass('bs-select');
});

</script>
