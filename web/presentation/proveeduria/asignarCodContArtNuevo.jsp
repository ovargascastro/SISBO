<%-- 
    Document   : asignarCodContable
    Created on : 25/09/2019, 12:06:39 AM
    Author     : oscar
--%>

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
        <title>Asignar Codigo Contable</title>
    </head>

    <body style="background-color: rgb(255,255,255);" onload="javascript:art()">
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
                <h4 class="text-center card-title">Listado de Artículos</h4>
                <div class="container">
                    <div class="row">
                        <div class="col text-center" id="tablaArticulos">
                            <div class="table-responsive" style="max-height: 350px; overflow: auto">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th class="text-center">Artículo</th>
                                            <th class="text-center">Sub-Familia</th>
                                            <th class="text-center">Sub-Familia<br>Descripción<br></th>
                                            <th class="text-center">Asignar Código Contable</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listaArticulos">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form id="formCodContable" action="javascript:asignarCodCont()">
            <div class="modal fade" role="dialog" tabindex="-1" id="modalCodContable">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Asignar Código Contable</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container">
                                <div class="form-row">
                                    <div class="col">
                                        <label>Código</label>
                                        <select id="selectConta" class="selectpicker form-control" 
                                                data-live-search="true" data-size="15" required>
                                            <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                        </select>
                                        <style>
                                            div.dropdown-menu.open { width: 150%; }
                                            ul.dropdown-menu.inner>li>a { white-space: initial; }
                                        </style>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-light" type="button" data-dismiss="modal" onclick="javascript:limpiar()">Cancelar</button>
                            <button class="btn btn-primary" type="submit">Agregar</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

        <script src="assets/js/articulosCodConta.js" type="text/javascript"></script>


    </body>
    <script>
                                document.getElementById("codContableMenu").style.color = "white";

                                function logged() {
        <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
        <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("8") && !aux.getDepartamento().getDeptoIdPk().equals("6")) { %>
                                    location.href = "presentation/notAccess.jsp";
        <%}%>
                                }

                                function selectConta() {
                                    $.ajax({type: "GET",
                                        url: "api/contables?filtro=" + " ",
                                        success: pb4,
                                        error: function (data) {
                                            alert('error');
                                        }
                                    });
                                }

                                function pb4(data) {

                                    var jsonData = JSON.stringify(data);
                                    $.each(JSON.parse(jsonData), function (idx, obj) {
                                        $("#selectConta").append('<option value="' + obj.cntIdPk + '">' + '➤ ' + obj.cntCodi + '-' + obj.cntDesc + '</option>');

                                    });
                                    $('#selectConta').selectpicker('refresh');

                                }

                                function picker() {
                                    $('#selectConta').addClass('selectpicker');
                                    $('#selectConta').attr('data-live-search', 'true');
                                }
    </script>
</html>
