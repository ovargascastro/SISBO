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

    <body style="background-color: rgb(255,255,255);">
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
                                                <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                            </select>
                                        </div>
                                        <div class="col"></div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <label>Unidad Usuaria</label>
                                            <select id="SelectDptos" class="selectpicker form-control" 
                                                    data-live-search="true" data-size="15" required>
                                                <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                            </select>
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
        <div class="col text-center">
                       <button class="btn btn-primary" id="btnBuscarExist" type="submit">Buscar</button>
&nbsp;                 <button class="btn btn-primary" id="btnGenerateReport" foraction="ReportePDF()" type="submit">Generar Reporte</button>
                     </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
        <script src="assets/js/existencias.js" type="text/javascript"></script>
    </body>
</html>

<script>
    document.getElementById("bodegasMenu").style.color = "white";
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
    $(document).ready(function () {
        $('#dtBasicExample').DataTable({
            "paging": false // false to disable pagination (or any other option)
        });
        $('.dataTables_length').addClass('bs-select');
    });

    function getStocks() {
        var depto = document.getElementById("SelectDptos").value;
        var bodeg = document.getElementById("SelectBodegas").value;
        $.ajax({type: "GET",
            url: "api/tomaFisica/" + bodeg + "/" + depto,
            success: listaExistencias
        });
    }

    //se listan las existencias en la tabla
    function listaExistencias(personas) {
        var listado = $("#listadoExistencias");
        listado.html("");
        personas.forEach((p) => {
            fila(listado, p);
        });
    }

    //se utliza para mostrar los datos en las filas de la funcion anterior
    function fila(listado, objeto) {
        var tr = $("<tr />");
        tr.html(
                "<td>" + objeto.sboTbBodega.bodeDesc + "</td>"
                + "<td>" + objeto.articulo.abaaTbDepartamento.deptoNomb + "</td>"
                + "<td>" + objeto.articulo.sboSicop.sicopDesc + "</td>"
                + "<td>" + objeto.sboTbEsta + "</td>");
        listado.append(tr);
    }

</script>
