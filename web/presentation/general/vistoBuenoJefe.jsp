<%-- 
    Document   : vistoBueno
    Created on : 15-oct-2019, 13:26:52
    Author     : ESCINF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html id="body">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <base href="http://localhost:8084/SISBO/" >
<title>Recepcion Solicitud Jefe</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    
</head>

<body style="background-color: rgb(255,255,255);">
   
   <%@ include file="/presentation/header.jsp" %>
    
    <div id="titulo">
        <div class="jumbotron">
            <h1>Visto Bueno de Solicitudes de Artículos por parte del Jefe</h1>
            <p>Listado de las solicitudes de artículos pendientes de Visto Bueno</p>
            <p></p>
        </div>
    </div>
    <form>
        <div class="card" id="formulario">
            <div class="card-body">
                <h4 class="text-center">Solicitudes de Artículos</h4>
                <div class="container">
                             <div class="row">
                    <div class="col">
                        <form>
                            <div><label>Buscar Número de Solicitud</label>
                                <div class="form-row">
                                    <div class="col"><input class="form-control" type="text" id="filtro"></div>
                                   <div class="col"><button class="btn btn-primary" type="button" onclick="javascript:buscarSolicitudVbJf()">Buscar</button></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                    <br>
                    <div class="form-row">
                        <div class="col text-center">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                             <th>ID<br><br></th>
                                             <th>Fecha<br><br></th>
                                            <th>Departamento<br><br></th>
                                            <th>Artículos<br><br></th>
                                            <th>Aprobar<br><br></th>
                                           
                                        </tr>
                                    </thead>
                                    <tbody  id="listSolArtJF">
                                
                                    </tbody>
                                </table>
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
                    
                    
            <div role="dialog" tabindex="-1" class="modal fade" id="Aprobar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Aprobación de Solitudes por parte del Jefe de departamento </h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                    <p><br>¿Desea Aprobar la solicitud seleccionada?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary bg-danger" type="button" data-dismiss="modal" onclick="javascript:abrirModalRechazar()">Rechazar</button>  
                        <button class="btn btn-success" type="button" onclick="javascript:actualizarEstadoVbJf()">Aprobar</button></div>
                </div>
            </div>
        </div>
                    
                    
      <div role="dialog" tabindex="-1" class="modal fade" id="Rechazar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Rechazar la Solicitudes Jefe</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Motivo del rechazo de la solicitud</p>
                        <div class="col"><input class="form-control" type="text" placeholder="Digite el motivo" id="motivoJF"></div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>  <button class="btn btn-primary bg-danger" type="button" onclick="javascript:actualizarEstadoRechazoJefe()">Rechazar</button></div>
                </div>
            </div>
        </div>

                    
                    
                    
                    <div class="form-row" id="linea">
                        <div class="col">
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" tabindex="-1" id="modalArticulo">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Información del Artículo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="form-row">
                                <div class="col">
                                    <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"><label class="form-check-label" for="formCheck-1">Proyecto?</label></div><label>Proyecto</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                            </div>
                            <div class="form-row">
                                <div class="col"><label>Artículo</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select><label>Marca</label>
                                    <input
                                        class="form-control" type="text" placeholder="Marca"><label>Serie</label><input class="form-control" type="text" placeholder="Serie"><label>Precio</label><input class="form-control" type="text" placeholder="Precio"><label>Unidad Usuaria</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                                <div
                                    class="col"><label>Descripción</label><input class="form-control" type="text" placeholder="Descripción"><label>Modelo</label><input class="form-control" type="text" placeholder="Modelo"><label>Cantidad</label><input class="form-control"
                                        type="number" placeholder="Cantidad"><label>Unidad de Medida</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select><label>Código Presupuestario</label>
                                    <input
                                        class="form-control" type="text" placeholder="Código Presupuestario"></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="button">Agregar</button></div>
            </div>
        </div>
        </div>
    </form>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/solicitudArticulo.js" type="text/javascript"></script>
</body>


<script>
    document.getElementById("solicitudArtMenu").style.color = "white";
    function listSoliArtJF(personas) {
        var listado = $("#listSolArtJF");
        listado.html("");
        personas.forEach((p) => {
            filaSolArtJF(listado, p);
        });
    }

    function filaSolArtJF(listado, objeto) {
        var tr = $("<tr />");
        tr.html(
                "<td>" + objeto.solArtiIdPk + "</td>"
                + "<td>" + formatDate(objeto.solArtiFechSoli) + "</td>"
                + "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>"
                + "<td><img src='assets/img/delivery-cart.png' onclick='articulosXSolicitud(\"" + objeto.solArtiIdPk + "\");'></td>"
                +"<td><img src='assets/img/edit.png' onclick='Aprobar(\"" + objeto.solArtiIdPk + "\");'></td>"
                );
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
