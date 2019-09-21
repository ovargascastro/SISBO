<%-- 
    Document   : ordenDeCompra
    Created on : 15-sep-2019, 21:52:19
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <base href="http://localhost:8084/SISBO/" >
        <title>Orden de Compra</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Orden de Compra por Bienes y/o Servicios</h1>
                <p>Rellenar el siguiente formulario correspondiente para ingresar una orden de compra</p>
                <p></p>
            </div>
        </div>
        <form>
            <div class="card" id="formulario">
                <div class="card-body">
                    <h4 class="text-center">Ingreso de Orden de Compra</h4>
                    <div class="container" id="contenedorEncabezado">
                        <div class="form-row">
                            <div class="col"><label>Fecha</label><input class="form-control" type="date"></div>
                            <div class="col"><label>Codigo Prov</label><input class="form-control" type="text" placeholder="Cod. Proveedor"></div>
                        </div>
                        <div class="form-row">
                            <div class="col"><label>Proveedor:</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select><label>Correo</label>
                                <input
                                    class="form-control" type="email" placeholder="email"><label>Plazo de Entrega</label><input class="form-control" type="text" placeholder="Plazo de entrega"><label>Sirvase entregar al señor(a)</label><input class="form-control" type="text" placeholder="Entregar a"></div>
                            <div class="col"><label>Cedula</label><input class="form-control" type="text" placeholder="Cedula"><label>Telefono</label><input class="form-control" type="text" placeholder="Telefono"><label>Fax</label><input class="form-control" type="text" placeholder="Fax"></div>
                        </div>
                        <div class="form-row" id="linea">
                            <div class="col">
                                <hr>
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
                        <div class="form-row" id="botonAgregar">
                            <div class="col text-center"><button class="btn btn-primary" type="button" onclick="abrirModalArt()">Agregar Articulo</button></div>
                        </div>
                        <div class="form-row" id="linea">
                            <div class="col">
                                <hr>
                            </div>
                        </div>
                        <div class="form-row" id="rowTabla">
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead class="text-center">
                                            <tr>
                                                <th>Cantidad<br><br></th>
                                                <th>Unidad<br>Medida</th>
                                                <th>Unidad<br>Usuaria</th>
                                                <th>Descripción<br><br></th>
                                                <th>Precio<br>Unitario</th>
                                                <th>Precio<br>Total</th>
                                                <th>Eliminar<br><br></th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center">
                                            <tr>
                                                <td>3</td>
                                                <td>und</td>
                                                <td>Acueducto</td>
                                                <td>Intrruptor termomagnetico<br>de enchufar QO 10 KA</td>
                                                <td>10125</td>
                                                <td>30375‬</td>
                                                <td><img src="assets/img/trash-delete.png"></td>
                                            </tr>
                                            <tr>
                                                <td>1</td>
                                                <td>und</td>
                                                <td>Proveeduría</td>
                                                <td>Gabinetes para interruptores<br>termomagneticos power</td>
                                                <td>95579</td>
                                                <td>95570</td>
                                                <td><img src="assets/img/trash-delete.png"></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="form-row text-center" id="botonGuardar">
                            <div class="col"><button class="btn btn-success" type="button">Guardar</button></div>
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
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="proyectoCheck"><label class="form-check-label" for="formCheck-1">Proyecto?</label></div>
                                        <div class="form-check" id="comboProy">
                                        <label>Proyecto</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select>
                                        </div>
                                    </div>
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
<script>
    function abrirModalEliminar() {
        $('#modalEliminar').modal('show');

    }

        $(document).ready(function(){
            $('#comboProy').hide();
            

        $('input[type="checkbox"]').click(function(){

            if($(this).prop("checked") == true){

               $('#comboProy').show();

            }

            else if($(this).prop("checked") == false){

                $('#comboProy').hide();

            }

        });

    });

    function abrirModalArt() {
        $('#modalArticulo').modal('show');
    }
</script>