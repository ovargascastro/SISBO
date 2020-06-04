<%-- 
    Document   : consumo
    Created on : 06/04/2020, 12:53:25 PM
    Author     : oscar
--%>

<%@page import="logic.AbaaTbPersona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" type="text/css" href="assets/css/Imprimir.css" media="print" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <!-- (Optional) Latest compiled and minified JavaScript translation files -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/i18n/defaults-*.min.js"></script>
        <title>Reporte por Departamento</title>
    </head>

    <body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Reporte de Consumo</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="cuerpoListaOrdenes">
            <div class="card-body">
                <h4 class="text-center card-title">Reporte de Consumo por Departamento</h4>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form id="existencias" action="javascript:getReporteDepartamento()" >
                                <div>
                                    <label>Artículo</label>
                                    <div class="form-row">
                                        <div class="col">
                                            <select id="selectSicopPicker" class="selectpicker form-control" 
                                                    data-live-search="true" data-size="15" required>
                                                <option value="0" selected disabled = "true" >Seleccione una opcion</option>
                                                <option value="all" >➤ Todos los artículos</option>
                                            </select>
                                            <style>
                                                div.dropdown-menu.open { width: 100%; }
                                                ul.dropdown-menu.inner>li>a { white-space: initial; }
                                            </style>
                                        </div>
                                        <div class="col">
                                            <div class="col text-center">
                                                <button class="btn btn-primary" id="btnBuscarExist" type="submit">Buscar</button>
                                            </div> 
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col">
                                        <label>Fecha Inicial</label>
                                        <input id="fechaInicio" class="form-control" type="date" 
                                               placeholder="Fecha Inicial" required>
                                        <label>Fecha Final</label>
                                        <input id="fechaFinal" class="form-control" type="date" 
                                               placeholder="Fecha Final" required>
                                    </div>
                                    <div class="col text-center">
                                        <br>
                                        <br>
                                        <div><p>Imprimir Reporte  <img id="pdf" src="assets/img/printer_1.png" onclick="javascript:imprimir()"> </p> </div>
                                       
                                    </div>
                                </div>
                                <div>
                                    <label>Unidad Usuaria</label>
                                    <div class="form-row">
                                        <div class="col">
                                            <select id="selectDptoPicker" class="selectpicker form-control" 
                                                    data-live-search="true" data-size="15" required>
                                                <option value="0" selected disabled = "true" >Seleccione una opcion</option>
                                            </select>
                                        </div>
                                        <div class="col">
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
                                            <th class="text-center">Artículo</th>
                                            <th class="text-center">Código Identificación</th>
                                            <th class="text-center">Código Clasificación</th>
                                            <th class="text-center">Cantidad</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listado">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div role="dialog" tabindex="-1" class="modal fade" id="ImprimirReporte" align="center">
            <div class="modal-dialog" role="document" align="center">
                <div class="row">
                    <div class="modal-content"> 
                        <div id="content">
                            <br>
                            <p>_______________________________________________________________________________<br></p>
                            <h3 class="text-center"><br><br>Municipalidad de Santo Domingo  <br></h3>
                            <h4 id="nombDepaModal" class="text-center"><br><br>Reporte de consumo de:  <br></h4>
                            <h4 id="fechaModal" class="text-center"><br><br>Desde  <br></h4>
                            <div class="col text-center" id="tablaArticulosReporte">
                                <div class="table-responsive" align="center">
                                    <p>_______________________________________________________________________________ <br><br><br><br><p>
                                    <table id="demo" class="table table-bordered" align="center">
                                        <thead >
                                            <tr>
                                                <th class="text-center">Artículo       <br><br></th>
                                                <th class="text-center">Código <br>de Identificación<br><br></th>
                                                <th class="text-center">Código <br>de Clasificación<br><br></th>
                                                <th class="text-center">Cantidad<br><br></th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaArticulosReporte">
                                        </tbody>
                                    </table>
                                    <br>
                                    <footer class="footer">
                                        <button class="btn btn-success" id="export">Imprimir</button>
                                    </footer>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>

        <script src="assets/js/jspdf.min.js"></script>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <script src="assets/js/consumo.js" type="text/javascript"></script>
    </body>

</html>
<script>
document.getElementById("reportes").style.color = "white";
function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
}

$(document).ready(function () {
    logged();
});
</script>
