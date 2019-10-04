<%-- 
    Document   : solicitudArticulos
    Created on : 03/10/2019, 10:39:59 PM
    Author     : oscar
--%>

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
    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>

        <div id="titulo">
            <div class="jumbotron">
                <h1>Solicitud de Artículos</h1>
                <p>Rellenar el formulario para realizar una solicitud de artículos en bodega</p>
                <p></p>
            </div>
        </div>
        <form>
            <div class="card" id="formulario">
                <div class="card-body">
                    <h4 class="text-center">Solicitud de Artículos</h4>
                    <div class="container" id="contenedorEncabezado">
                        <div class="form-row">
                            <div class="col"><label>Artículo</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                            <div
                                class="col"><label>Cantidad</label><input class="form-control" type="number" placeholder="Cantidad"></div>
                        </div>
                        <div class="form-row text-center" id="rowBtnAgregar">
                            <br>
                            <br>
                            <div class="col"><button class="btn btn-primary text-center" id="btnAgregarArt" type="button">Agregar Articulo</button></div>
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
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Cell 1</td>
                                            </tr>
                                            <tr>
                                                <td>Cell 3</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Cantidad</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="text-center">
                                                <td>Cell 1</td>
                                            </tr>
                                            <tr>
                                                <td>Cell 3</td>
                                            </tr>
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
                        <div class="form-row text-center" id="botonGuardar">
                            <div class="col"><button class="btn btn-success" type="button">Solicitar</button></div>
                        </div>
                    </div>
                </div>
            </div>
           
        </form>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>