<%-- 
    Document   : ordenDeCompra
    Created on : 15-sep-2019, 21:52:19
    Author     : oscar
--%>
<%@page import="logic.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        
        
        <title>Orden de Compra</title>
    </head>
    <body onload="cargarSelectsOrden()">
        <%@ include file="/presentation/header.jsp" %>

        <div id="titulo">
            <div class="jumbotron">
                <h1>Orden de Compra por Bienes y/o Servicios</h1>
                <p>Rellenar el siguiente formulario correspondiente para ingresar una orden de compra</p>
                <p></p>
            </div>
        </div>
        <form action="javascript:agregarOrdenCompra()">
            <div class="card" id="formulario">
                <div class="card-body">
                    <h4 class="text-center">Ingreso de Orden de Compra</h4>
                    <div class="container" id="contenedorEncabezado">
                        <div class="form-row">
                            <div class="col">
                                <label>Fecha</label>
                                <input class="form-control" type="date" id="fechaOrden" required>
                            </div>
                            <div class="col">
                                <label>Código Proveedor</label>
                                <input class="form-control" type="text" placeholder="Codigo Proveedor" id="codigoProveedor" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <label>Proveedor:</label>
                                <select class="form-control" id="selectProveedores" onchange="selecProve()" >
                                    <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                </select>

                                <label>Plazo de Entrega</label>
                                <input class="form-control" type="text" placeholder="Plazo de entrega" id="plazoEntrega">

                                <label>Sírvase entregar a</label>
                                <input class="form-control" type="text" placeholder="Municipalidad de Santo Domingo" value="Municipalidad de Santo Domingo" id="entregarA" readonly="readonly">
                            </div>
                            <div class="col">
                                <label>Identificación</label>
                                <input class="form-control" type="text" placeholder="Identificación" id="Cedula" readonly="readonly">

                                <label>Correo electrónico</label> 
                                <input class="form-control" type="email" placeholder="email" id="email" readonly="readonly">

                                <label>Teléfono</label>
                                <input class="form-control" type="text" placeholder="Teléfono" id="Telefono" readonly="readonly">

                                <label>Fax</label>
                                <input class="form-control" type="text" placeholder="Fax" id="Fax" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Proyectos</th>
                                            </tr>
                                        </thead>
                                        <tbody id="proyectosRow">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Unidades Usuarias</th>
                                            </tr>
                                        </thead>
                                        <tbody id="departamentosRow">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="form-row" id="linea">
                            <div class="col">
                                <hr>
                            </div>
                        </div>
                        <div class="form-row" id="botonAgregar">
                            <div class="col text-center"><button class="btn btn-primary" type="button" onclick="abrirModalArt()">Agregar Artículo</button></div>
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
                                                <th>Artículo<br><br></th>
                                                <th>Descripción<br><br></th>
                                                <th>Unidad<br>Medida</th>
                                                <th>Unidad<br>Usuaria</th>
                                                <th>Precio<br>Unitario</th>
                                                <th>Precio<br>Total</th>
                                                <th>Eliminar<br><br></th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center" id="listadoArticulos">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="form-row text-center" id="botonGuardar">
                            <div class="col"><button class="btn btn-success" type="submit">Guardar</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </form>          


        <div class="modal fade" role="dialog" tabindex="-1" id="modalArticulo" data-backdrop="static">
            <div class="modal-dialog" role="document">
                <form id="articulosOrden" action="javascript:agregarArticuloTemporal()">
                    <div class="modal-content">

                        <div class="modal-header">

                            <h4 class="modal-title">Información del Artículo</h4>
                        </div>
                        <div class="modal-body">
                            <div class="container">

                                <div class="form-row">
                                    <div class="col">
                                        <div class="form-check"><input class="form-check-input" type="checkbox" id="proyectoCheck">
                                            <label class="form-check-label" for="formCheck-1">Seleccione si pertene a un proyecto</label></div>
                                        <div class="form-check" id="comboProy">
                                            <label>Proyecto</label>
                                            <select class="form-control" id="selectProyectos">
                                                <option values="0" selected disabled = "true">Seleccione una opcion</option>

                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="col">
                                        <label>Artículo</label>
                                        <select class="form-control" id="selectCatalogoArticulos" required>
                                            <option values="0" selected disabled = "true" >Seleccione una opcion</option>

                                        </select>

                                        <label>Marca</label>
                                        <input class="form-control" type="text" placeholder="Marca" id="Marca">

                                        <label>Cantidad</label>
                                        <input class="form-control" type="number" placeholder="Cantidad" id="Cantidad" required>

                                        <label>Unidad Usuaria</label>
                                        <select class="form-control" id="selectDeptos" required>
                                            <option values="0" selected disabled = "true">Seleccione una opcion</option>

                                        </select>
                                    </div>

                                    <div class="col">
                                        <label>Descripción</label>
                                        <input class="form-control" type="text" placeholder="Descripción" id="Descripcion" required>

                                        <label>Modelo</label>
                                        <input class="form-control" type="text" placeholder="Modelo" id="Modelo">

                                        <label>Precio</label>
                                        <input class="form-control" type="number" placeholder="Precio" id="Precio" required>
                                        
                                        <label>Unidad de Medida</label>
                                        <select class="form-control" id="selectUnidadMedida" required>
                                            <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                            <option value="Unidad" >Unidad</option>
                                            <option value="Kilo">Kilo</option>
                                            <option value="Metro">Metro</option>
                                        </select>

                                        <label>Código Presupuestario</label>
                                        <input class="form-control" type="text" placeholder="Código Presupuestario" id="codPresupuestario" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light" type="button" data-dismiss="modal" onclick="javascript:limpiar()">Cancelar</button>
                            <button class="btn btn-primary" type="submit">Agregar</button></div>

                    </div>
                </form>
            </div>
        </div>





        
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        
        <script src="assets/js/departamentos.js" type="text/javascript"></script>
        <script src="assets/js/catalogos.js" type="text/javascript"></script>
        <script src="assets/js/proyectos.js" type="text/javascript"></script>
        <script src="assets/js/proveedores.js" type="text/javascript"></script>
        <script src="assets/js/ordenCompra.js" type="text/javascript"></script>
        
        
    </body>

</html>

<script>

                                function abrirModalEliminar() {
                                    $('#modalEliminar').modal('show');


                                }


                                $(document).ready(function () {

                                    $('#comboProy').hide();

                                    $('input[type="checkbox"]').click(function () {

                                        if ($(this).prop("checked") == true) {

                                            $('#comboProy').show();

                                        } else if ($(this).prop("checked") == false) {

                                            $('#comboProy').hide();

                                        }

                                    });

                                });

                                function abrirModalArt() {
                                    $('#modalArticulo').modal('show');

                                }

                                function cargarSelectsOrden() {
                                    selectProveedores();
                                    selectDeptos();
                                    selectProyectos();
                                    selectCatArticulos();
    <%Model.instance().reiniciaLista();%>
                                }

                                function listaArticulosTemporales(art) {
                                    var listado = $("#listadoArticulos");
                                    listado.html("");
                                    art.forEach((a) => {
                                        filaArticulosTemporales(listado, a);
                                    });
                                    agregaDepartamento(art);
                                    agregaProyecto(art);
                                }

                                var array = [];
                                var x;
                                function filaArticulosTemporales(listado, articulo) {
                                    var tr = $("<tr />");
                                    tr.html(
                                            "<td>" + articulo.artCant + "</td>"
                                            + "<td>" + articulo.sboTbCatArticulo.catDesc + "</td>"
                                            + "<td>" + articulo.artDesc + "</td>"
                                            + "<td>" + articulo.artUnidadMedida + "</td>"
                                            + "<td>" + articulo.abaaTbDepartamento.deptoNomb + "</td>"
                                            + "<td>" + articulo.artPrecio + "</td>"
                                            + "<td>" + (articulo.artPrecio * articulo.artCant) + "</td>"
                                            + "<td><img src='assets/img/trash-delete.png' onclick='eliminarArt(\"" + articulo.artIdPk + "\");'></td>");
                                    listado.append(tr);

                                }

</script>
