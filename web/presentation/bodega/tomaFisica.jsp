<%-- 
    Document   : tomaFisica
    Created on : 03/05/2020, 07:15:03 PM
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

        <title>Toma Física</title>
    </head>

    <body style="background-color: rgb(255,255,255);" onload="logged();">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Toma Física</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="cuerpoListaOrdenes">
            <div class="card-body">
                <h4 class="text-center card-title">Articulos </h4>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form id="existencias" action="javascript:getStocks()" >
                                <div>
                                    <label>Bodega</label>
                                    <div class="form-row">
                                        <div class="col">
                                            <select class="form-control" id="SelectBodegas" required>
                                                <option value="0" selected disabled = "true" >Seleccione una opcion</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <div class="col text-center">
                                                <button class="btn btn-primary" id="btnBuscarExist" type="submit">Buscar</button>
                                            </div> 
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col">
                                            <label>Unidad Usuaria</label>
                                            <select id="SelectDptos" class="selectpicker form-control" 
                                                    data-live-search="true" data-size="15" required>
                                                <option value="0" selected disabled = "true" >Seleccione una opcion</option>
                                            </select>
                                        </div>

                                        <div class="col text-center">
                                            <br>
                                            <br>
                                            <div><p>Imprimir Reporte  <img id="pdf" src="assets/img/printer_1.png" onclick="javascript:imprimir()"> </p> </div>

                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col text-center" id="tablaOrdenes">
                            <div class="table-responsive " style="max-height: 350px; overflow: auto">
                                <div class="table-responsive">
                                    <table class="table" id="myTable">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Bodega</th>
                                                <th class="text-center">Departamento</th>
                                                <th class="text-center">Descripcion</th>
                                                <th class="text-center">Cantidad Total en Existencia</th>      
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
        </div>
        
        <div role="dialog" tabindex="-1" class="modal fade" id="ImprimirReporte" align="center">
            <div class="modal-dialog" role="document" align="center">
                <div class="row">
                    <div class="modal-content"> 
                        <div id="content">
                            <p>_______________________________________________________________________________<br></p>
                            <h3 class="text-top"><br><br>Municipalidad de Santo Domingo  <br></h3>
                            <h4 class="text-center"><br><br>Reporte toma física  <br></h4>
                          
                            <div class="col text-center" id="tablaArticulosReporte">
                                <div class="table-responsive" align="center">
                                    <p>_______________________________________________________________________________ <br><br><br><br><p>
                                    <table id="demo" class="table table-bordered" align="center">
                                        <thead >
                                            <tr>
                                                <th class="text-center">Bodega</th>
                                                <th class="text-center">Departamento</th>
                                                <th class="text-center">Descripcion</th>
                                                <th class="text-center">Total </th> 
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

        <script src="assets/js/jspdf.min.js"></script>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <script src="assets/js/existencias.js" type="text/javascript"></script>
        <script src="assets/js/tomaFisica.js" type="text/javascript"></script>
    </body>
</html>

<script>
    function logged() {
        <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
        <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("5") && !aux.getDepartamento().getDeptoIdPk().equals("6")) { %>
            location.href = "presentation/notAccess.jsp";
        <%}%>
    }

</script>
