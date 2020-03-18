<!DOCTYPE html>

<html id="body">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
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
                                            <select id="selectSicop" class="form-control" >
                                            </select>
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
                        <div class="col"></div>
                    </div>
                    <div class="row">
                        <div class="col text-center" id="tablaOrdenes">
                            <div class="table-responsive">
                                <table class="table" id="myTable">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Bodega</th>
                                            <th class="text-center">Departamento</th>
                                            <th class="text-center">Descripcion</th>
                                            <th class="text-center">Cantidad</th>
                                            <th class="text-center">Editar Cantidad</th>
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

        <div class="modal fade" role="dialog" tabindex="-1" id="modalEditarExist">
            <form id="actualizaExist" action="javascript:actualizarExistencia();">
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
                                <form>
                                    <div class="form-row">
                                        <div class="col"><label>Existencias Actuales</label>
                                            <input class="form-control" type="text" id="existAct" readonly></div>
                                        <div class="col"><label>Nueva Cantidad</label>
                                            <input class="form-control" type="number" min="0" id="nuevExist" required></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>
                            <button class="btn btn-primary" type="submit">Guardar</button></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
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


</script>
