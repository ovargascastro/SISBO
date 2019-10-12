<%-- 
    Document   : ingresoArticulosNuevos
    Created on : 15-sep-2019, 21:58:32
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
    <title>Lista Órdenes de Compra</title>

</head>

<body style="background-color: rgb(255,255,255);">
    <%@ include file="/presentation/header.jsp" %>

    <div id="titulo">
        <div class="jumbotron">
            <h1>Solicitudes de artículos</h1>
            <p></p>
            <p></p>
        </div>
    </div>
    <div class="card" id="cuerpoListaOrdenes">
        <div class="card-body">
            <h4 class="text-center card-title">Listado de solicitudes de artículos</h4>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <form>
                            <div><label>Número de Solicitud</label>
                                <div class="form-row">
                                    <div class="col"><input class="form-control" type="text" id="filtro"></div>
                                    <div class="col"><button class="btn btn-primary" type="button" onclick="javascript:buscarListaSolicitudes()">Buscar</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col text-center" id="tablaSolicitudes">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="text-center">Número<br>de solicitud</th>
                                        <th class="text-center">Fecha</th>
                                        <th class="text-center">Unidad usuaria</th>
                                        <th class="text-center">Artículos</th>
                                    </tr>
                                </thead>
                                <tbody id="listSolArt">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" id="listaArtxSol">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
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
                                                <th>Cantidad<br><br></th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaArticulosSolicitud">
             
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button></div>
    </div>
  </div>
</div>
    

    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/solicitudArticulo.js" type="text/javascript"></script>
</body>
<script>
    function listSoliArt(personas) {
        var listado = $("#listSolArt");
        listado.html("");
        personas.forEach((p) => {
            filaSolArt(listado, p);
        });
    }

    function filaSolArt(listado, objeto) {
        var tr = $("<tr />");
        tr.html(
                "<td>" + objeto.solArtiIdPk + "</td>"
                + "<td>" + formatDate(objeto.solArtiFechSoli) + "</td>"
                + "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>"
                + "<td><img src='assets/img/delivery-cart.png' onclick='articulosXSolicitud(\"" + objeto.solArtiIdPk + "\");'></td>");
        listado.append(tr);

    }
    
    //onclick='articulosXSolicitud(\"" + objeto.solArtiIdPk + "\");'
    
    
        function listaArticulosxSol(personas) {
        var listado = $("#listaArticulosSolicitud");
        listado.html("");
        personas.forEach((p) => {
            filaArticulos(listado, p);
        });
    }
    
    

    function filaArticulos(listado, objeto) {
        var tr = $("<tr />");
        tr.html(
                "<td>" + objeto.sboTbArticulo.artDesc + "</td>"
                + "<td>" + objeto.solArtiCant + "</td>");

        listado.append(tr);

    }
</script>
</html>
