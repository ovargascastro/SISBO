<%-- 
    Document   : solicitudArticulos
    Created on : 03/10/2019, 10:39:59 PM
    Author     : oscar
--%>
<%@page import="logic.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Solicitud de Articulos</title>
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
    </head>
    <body style="background-color: rgb(255,255,255);" onload="cargarSelectsSolArt()">
        <%@ include file="/presentation/header.jsp" %>

        <div id="titulo">
            <div class="jumbotron">
                <h1>Solicitud de Artículos</h1>
                <p>Rellenar el formulario para realizar una solicitud de artículos en bodega</p>
                <p></p>
            </div>
        </div>
        
        <form action="javascript:agregarSolicitudArticulo()">
            <div class="card" id="formulario">
                <div class="card-body">
                    <h4 class="text-center">Solicitud de Artículos</h4>
                    
                    <div class="container" id="contenedorEncabezado">
                        
                            
                            <div class="form-row">
                                <%-- 
                                <div class="col">
                                    <label>Fecha</label>
                                <input class="form-control" type="date" id="fechaSolArt" required>
                                </div>
                                --%>
                                <div class="col">
                                    <label>Unidad Usuaria</label>
                                    <select class="form-control" id="selectDeptos" onchange="selecArt()">
                                        <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label>Artículo</label>
                                    <select class="form-control" id="selectArt" onchange="getExistencias()">
                                        <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label>Existencias</label>
                                    <input class="form-control" type="text" placeholder="Existencias" readonly="readonly" id="cantidadExist">
                                </div>
                                <div class="col">
                                    <label>Cantidad</label>
                                    <input class="form-control" type="number" placeholder="Cantidad" id="cantidad">
                                </div>
                            </div>
                            <div class="form-row text-center" id="rowBtnAgregar">
                                <br>
                                <br>
                                <div class="col">
                                    <button class="btn btn-primary text-center" id="btnAgregarArt" type="button" onclick="javascript:agregarArtTemp()">Agregar Articulo</button>
                                </div>
                            </div>
                        
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
                                                <th>Artículo</th>
                                                <th>Cantidad</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center" id="listArt">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <%--
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Cantidad</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center" id="listCant">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            --%>
                        </div>

                        <div class="form-row" id="linea">
                            <div class="col">
                                <hr>
                            </div>
                        </div>
                        <div class="form-row text-center" id="botonGuardar">
                            <div class="col">
                                <button class="btn btn-success" type="submit">Solicitar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/departamentos.js" type="text/javascript"></script>
        <script src="assets/js/solicitudArticulo.js" type="text/javascript"></script>
    </body>

</html>

<script>
                                    function cargarSelectsSolArt() {
                                        selectDeptos();
                                    }

                                    function listaArtTemp(art) {
                                        var listado = $("#listArt");
                                        listado.html("");
                                        art.forEach((a) => {
                                            filaArtTemp(listado, a);
                                        });
                                        agregaDepartamento(art);
                                        agregaProyecto(art);
                                    }

                                    var array = [];
                                    var x;
                                    function filaArtTemp(listado, articulo) {
                                        var tr = $("<tr />");
                                        tr.html(
                                                "<td>" + articulo.artDesc + "</td>"
                                                +"<td>" + articulo.cantSolArt + "</td>");
                                        listado.append(tr);
                                    }
                                    
                                    
</script>