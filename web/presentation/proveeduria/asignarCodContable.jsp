<%-- 
    Document   : asignarCodContable
    Created on : 25/09/2019, 12:06:39 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="http://localhost:8084/SISBO/" >
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <title>Asignar Codigo Contable</title>
</head>

<body style="background-color: rgb(255,255,255);">
    <%@ include file="/presentation/header.jsp" %>
    <div id="titulo">
        <div class="jumbotron">
            <h1>Asignar Códigos Contables</h1>
            <p></p>
            <p></p>
        </div>
    </div>
    <div class="card" id="cuerpoListaOrdenes">
        <div class="card-body">
            <h4 class="text-center card-title">Listado de Ordenes de Compra</h4>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <form>
                            <div><label>Número Orden de Compra</label>
                                <div class="form-row">
                                    <div class="col"><input class="form-control" type="text" id="filtro"></div>
                                    <div class="col"><button class="btn btn-primary" type="button" onclick="javascript:estadoConta()">Búscar</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col text-center" id="tablaOrdenes">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="text-center">Número<br>de Orden</th>
                                        <th class="text-center">Fecha</th>
                                        <th class="text-center">Estado</th>
                                        <th class="text-center">Articulos</th>
                                    </tr>
                                </thead>
                                <tbody id="listaordenesc">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="listaArticulos">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="javascript:asignaCodContable()">
                    <div class="modal-header">
                        <h4 class="modal-title">Listado de Articulos</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container text-center">

                            <div class="row">
                                <div class="col">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Articulo<br><br></th>
                                                    <th>Sub-Familia<br><br></th>
                                                    <th>Sub-Familia<br>Descripcion<br></th>
                                                    <th>Codigo Contable<br><br></th>
                                                </tr>
                                            </thead>

                                            <tbody id="listaArticulosOrdenC">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Asignar Codigos</button></div>
                </form>
            </div>
        </div>
    </div>




    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/ordenCompra.js" type="text/javascript"></script>
</body>
<script>
                                        function listaOrdenes(personas) {
                                            var listado = $("#listaordenesc");
                                            listado.html("");
                                            personas.forEach((p) => {
                                                filaOrdenes(listado, p);
                                            });
                                        }

                                        function filaOrdenes(listado, objeto) {
                                            var tr = $("<tr />");
                                            tr.html(
                                                    "<td>" + objeto.ocIdPk + "</td>"
                                                    + "<td>" + formatDate(objeto.ocFecha) + "</td>"
                                                    + "<td>" + objeto.ocEsta + "</td>"
                                                    + "<td><img src='assets/img/delivery-cart.png' onclick='articulosXordenConta(\"" + objeto.ocIdPk + "\");'></td>");
                                            listado.append(tr);

                                        }


                                        function listaArticulos(personas) {
                                            var listado = $("#listaArticulosOrdenC");
                                            listado.html("");
                                            personas.forEach((p) => {
                                                filaArticulos(listado, p);
                                            });
                                        }

                                        var cont = 0;
                                        var articulosArray = [];
                                        function filaArticulos(listado, objeto) {
                                            articulosArray.push(objeto);
                                            cont = cont + 1;
                                            var tr = $("<tr />");
                                            tr.html(
                                                    "<td>" + objeto.sboTbCatArticulo.catDesc + "</td>"
                                                    + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiIdPk +"</td>"
                                                    + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiDesc + "</td>"
                                                    + "<td><input type='text' class='form-control' required='required' id=" + cont + "></td>");
                                            listado.append(tr);

                                        }

                                        var actual = 1;
                                        function asignaCodContable() {

                                            for (i = 0; i < cont; i++) {

                                                var objeto = articulosArray.shift();
                                                SboTbArticulo = {
                                                    artIdPk: objeto.artIdPk,
                                                    artDesc: objeto.artDesc,
                                                    artCodiCont: $("#" + actual).val(),
                                                    sboTbOrdenCompra:{
                                                        ocIdPk: objeto.sboTbOrdenCompra.ocIdPk
                                                    }
                                                };
                                                $.ajax({type: "PUT",
                                                    url: "api/artxordenc",
                                                    data: JSON.stringify(SboTbArticulo),
                                                    contentType: "application/json",
                                                    success: estadoConta,
                                                    error: function (jqXHR) {
                                                        alert(errorMessage(jqXHR.status));
                                                    }
                                                });
                                                actual = actual + 1;
                                            }

                                            $('#listaArticulos').modal('hide');
                                        }
</script>
</html>
