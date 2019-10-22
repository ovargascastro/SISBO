<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="http://localhost:8084/SISBO/" >
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" type="text/css" href="assets/css/Imprimir.css" media="print" />
    <title>Recepcion Solic TI</title>

</head>
<body style="background-color: rgb(255,255,255);">
    <%@ include file="/presentation/header.jsp" %>
    <div id="titulo">
        <div class="jumbotron">
            <h1>Solicitudes de Artículos</h1>
            <p>Listado de las solicitudes de artículos pendientes</p>
            <p></p>
        </div>
    </div>
    <form>
        <div class="card" id="formulario">
            <div class="card-body">
                <h4 class="text-center">Solicitudes de Artículos</h4>
                <div class="container" id="contenedorEncabezado">
                    <div class="form-row" id="linea">
                        <div class="col">
                            <hr>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col text-center">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Departamento<br><br></th>
                                            <th>Artículos<br><br></th>
                                            <th>Aprobar<br><br></th>
                                            <th>Rechazar<br><br></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="text-center">
                                            <td>Cell 1</td>
                                            <td>Cell 1</td>
                                            <td>Cell 1</td>
                                            <td>Cell 1</td>
                                        </tr>
                                        <tr>
                                            <td>Cell 3</td>
                                            <td>Cell 3</td>
                                            <td>Cell 3</td>
                                            <td>Cell 3</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" role="dialog" tabindex="-1" id="modalInfoArticulo">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <div class="form-row">
                                        <div class="col">
                                            <h4 class="text-center">Información del artículo</h4>
                                        </div>
                                    </div>
                                    <h4 class="modal-title"></h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                                <div class="modal-body">
                                    <div class="form-row" id="infoArticulo">
                                        <div class="col"><label>Articulo</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select><label>Código Factura</label>
                                            <input
                                                class="form-control" type="text" placeholder="Código Factura"><label>Cantidad</label><input class="form-control" type="number" placeholder="Cantidad"></div>
                                        <div class="col"><label>Descripción</label><input class="form-control" type="text" placeholder="Descripcion"><label>Precio</label><input class="form-control" type="text" placeholder="Precio"><label>Unidad de Medida</label>
                                            <select
                                                class="form-control">
                                                <optgroup label="This is a group">
                                                    <option value="12" selected="">This is item 1</option>
                                                    <option value="13">This is item 2</option>
                                                    <option value="14">This is item 3</option>
                                                </optgroup>
                                                </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button class="btn btn-primary" type="button">Agregar</button></div>
                            </div>
                        </div>
                    </div>
                    <div class="form-row" id="linea">
                        <div class="col">
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" tabindex="-1" id="modalArticulo">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Información del Artículo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="form-row">
                                <div class="col">
                                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"><label class="form-check-label" for="formCheck-1">Proyecto?</label></div><label>Proyecto</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                            </div>
                            <div class="form-row">
                                <div class="col"><label>Artículo</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select><label>Marca</label>
                                    <input
                                        class="form-control" type="text" placeholder="Marca"><label>Serie</label><input class="form-control" type="text" placeholder="Serie"><label>Precio</label><input class="form-control" type="text" placeholder="Precio"><label>Unidad Usuaria</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                                <div
                                    class="col"><label>Descripción</label><input class="form-control" type="text" placeholder="Descripción"><label>Modelo</label><input class="form-control" type="text" placeholder="Modelo"><label>Cantidad</label><input class="form-control"
                                        type="number" placeholder="Cantidad"><label>Unidad de Medida</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select><label>Código Presupuestario</label>
                                    <input
                                        class="form-control" type="text" placeholder="Código Presupuestario"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="button">Agregar</button></div>
            </div>
        </div>
        </div>
    </form>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>