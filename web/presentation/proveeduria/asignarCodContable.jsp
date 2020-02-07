<%-- 
    Document   : asignarCodContable
    Created on : 25/09/2019, 12:06:39 AM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html id="body">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
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
                <h4 class="text-center card-title">Listado de Órdenes de Compra</h4>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form>
                                <div><label>Número Orden de Compra</label>
                                    <div class="form-row">
                                        <div class="col"><input class="form-control" type="text" id="filtro"></div>
                                        <div class="col"><button class="btn btn-primary" type="button" onclick="javascript:estadoConta()">Buscar</button></div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <br>
                                            <img src="assets/img/information.png" onclick="$('#information').modal('show');"> 
                                        </div>
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
                                            <th class="text-center">Proveedor</th>
                                            <th class="text-center">Precio<br>Total</th>
                                            <th class="text-center">Estado</th>
                                            <th class="text-center">Artículos</th>
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
                            <h4 class="modal-title">Listado de Artículos</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                        <div class="modal-body">
                            <div class="container text-center">

                                <div class="row">
                                    <div class="col">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Artículo<br><br></th>
                                                        <th>Sub-Familia<br><br></th>
                                                        <th>Sub-Familia<br>Descripción<br></th>
                                                        <th>Código Contable<br><br></th>
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

                    <div class="modal fade" role="dialog" tabindex="-1" id="information">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Información/Ayuda</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <form>
                        <div class="container">
                            <div class="form-row">
                                <div class="col">
                                           <p class="font-italic">
                                               Digite un número de solicitud y haga clic en el botón Buscar.<br>
                                               De no digitar un número se listarán todas las solicitudes.
                                           </p>
                                </div>
                               
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button></div>
            </div>
        </div>
    </div>



        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>


        <script src="assets/js/ordenCompra.js" type="text/javascript"></script>


    </body>
    <script>
                                            document.getElementById("ordenCompraMenu").style.color = "white";
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
                                                        + "<td>" + objeto.abaaTbProveedor.proveNomb + "</td>"
                                                        + "<td>" + objeto.ocPrecTota + "</td>"
                                                        + "<td>" + objeto.ocEsta + "</td>"
                                                        + "<td><img src='assets/img/delivery-cart.png' onclick='articulosXordenConta(\"" + objeto.ocIdPk + "\");'></td>");
                                                listado.append(tr);

                                            }

                                            var num = 0;
                                            function listaArticulos(personas) {
                                                var listado = $("#listaArticulosOrdenC");
                                                listado.html("");
                                                personas.forEach((p) => {
                                                    filaArticulos(listado, p);
                                                    num = num + 1;

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
                                                        + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiIdPk + "</td>"
                                                        + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiDesc + "</td>"
                                                        + "<td><select class='form-control' id='selectConta" + cont + "' required><option values='0' selected disabled = 'true'>Seleccione una opcion</option></select></td>");

                                                listado.append(tr);
                                                cargarSelectConta("#selectConta" + cont);
                                            }

                                            var actual = 1;
                                            function asignaCodContable() {

                                                for (i = 0; i < cont; i++) {
                                                    var codConta = document.getElementById("selectConta" + actual).value;
                                                    var objeto = articulosArray.shift();
                                                    SboTbArticulo = {
                                                        artIdPk: objeto.artIdPk,
                                                        artDesc: objeto.artDesc,
                                                        artCodiCont: codConta,

                                                        sboTbOrdenCompra: {
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

                                            function cargarSelectConta(conta) {
                                                $.ajax({type: "GET",
                                                    url: "api/contables?filtro=" + " ",
                                                    success: function (data) {
                                                        $.each(data, function (key, c) {
                                                            $(conta).append('<option value=' + c.cntIdPk + '>' + c.cntCodi + ' - ' + c.cntDesc + '</option>');
                                                        });
                                                    },
                                                    error: function (data) {
                                                        alert('error');
                                                    }
                                                });
                                            }
    </script>
</html>
