<%-- 
    Document   : administracionCatalogos
    Created on : 15-sep-2019, 22:03:11
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Administracion de Catalogos</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Administración de Catálogos</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <div class="card" id="catalogos">
            <div class="card-body">
                <div class="container" id="contenedorCatalogos">
                    <div class="row">
                        <div class="col text-center">
                            <h4 class="text-center">Desplegar catálogo de</h4>
                            <form id="busqueda">
                                <div class="form-row">
                                    <div class="col text-center"><select class="form-control" id="selectcatalogos"><optgroup label="Catalogos"><option value="1" selected="">Familia</option><option value="2">Sub-Familia</option><option value="3">Artículo</option></optgroup></select>
                                        <div class="form-row">
                                            <div class="col"><br><input class="form-control" type="text" id="filtro"></div>
                                        </div>
                                    </div>
                                    <div class="col text-left"><button class="btn btn-primary border-light" type="button" onclick="javascript:concatenarBusqueda()">Búscar &nbsp;<img id="magnifier" src="assets/img/magnifier.png"></button></div>
                                </div>
                            </form>
                        </div>
                        <div class="col">
                            <h4>Agregar Registro a un catalogo</h4><br><br><button class="btn btn-primary" type="button">Registrar</button></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card" id="tablaCatalogos" style="background-color: rgb(255,255,255);">
            <div class="card-body">
                <h4 id="catalogode" class="card-title">&nbsp;</h4>
                <div class="container" id="contenedorTabla">
                    <div class="row">
                        <div class="col text-center">
                            <div class="table-responsive " style="max-height: 350px; overflow: auto">
                                <div class="table-responsive">

                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Código</th>
                                                <th>Descripción</th>
                                                <th>Editar</th>
                                                <th>Eliminar</th>
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
        </div>


        <div role="dialog" tabindex="-1" class="modal fade" id="modalEliminar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Eliminar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Desea eliminar el registro seleccionado?</p>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button><button class="btn btn-primary bg-danger" type="button">Eliminar</button></div>
                </div>
            </div>
        </div>

        <div class="modal fade" role="dialog" tabindex="-1" id="modalEditarFam">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <form>
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Código Fam<br></label><input class="form-control" type="text" id="codigoFamilia"></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripFamilia"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button><button class="btn btn-primary" type="button">Save</button></div>
                </div>
            </div>
        </div>

        <div class="modal fade" role="dialog" tabindex="-1" id="modalSubFam">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <form>
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Código Sub-Fam<br></label><input class="form-control" type="text" id="codigoSubFam"></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripSubFam"></div>
                                </div>
                                <div class="form-row">

                                    <div class="col"><label>CodFamilia<br></label><select class="form-control" id="selectFamilias"></select></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="button">Guardar</button></div>
                </div>
            </div>
        </div>


        <div class="modal fade" role="dialog" tabindex="-1" id="modalArticulo">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <form>
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripArt"></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Sub-Familia<br></label>
                                        <select class="form-control" id="selectSubFam"></select></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="button">Guardar</button></div>
                </div>
            </div>
        </div>

        

        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/catalogos.js" type="text/javascript"></script>
    </body>
    <script>


                                    function listaFam(personas) {
                                        var listado = $("#listado");
                                        listado.html("");
                                        personas.forEach((p) => {
                                            filaFam(listado, p);
                                        });
                                    }

                                    function filaFam(listado, objeto) {
                                        var tr = $("<tr />");
                                        tr.html(
                                                "<td>" + objeto.famiIdPk + "</td>"
                                                + "<td>" + objeto.famiDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.famiIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/trash-delete.png' onclick='del(\"" + objeto.famiIdPk + "\");'></td>");
                                        listado.append(tr);
                                    }

                                    function listaSubFam(personas) {
                                        var listado = $("#listado");
                                        listado.html("");
                                        personas.forEach((p) => {
                                            filaSubFam(listado, p);
                                        });
                                    }

                                    function filaSubFam(listado, objeto) {
                                        var tr = $("<tr />");
                                        tr.html(
                                                "<td>" + objeto.subFamiIdPk + "</td>"
                                                + "<td>" + objeto.subFamiDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.subFamiIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/trash-delete.png' onclick='del(\"" + objeto.subFamiIdPk + "\");'></td>");
                                        listado.append(tr);
                                    }

                                    function listaCatArt(personas) {
                                        var listado = $("#listado");
                                        listado.html("");
                                        personas.forEach((p) => {
                                            filaCatArt(listado, p);
                                        });
                                    }

                                    function filaCatArt(listado, objeto) {
                                        var tr = $("<tr />");
                                        tr.html(
                                                "<td>" + objeto.catIdPk + "</td>"
                                                + "<td>" + objeto.catDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.catIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/trash-delete.png' onclick='del(\"" + objeto.catIdPk + "\");'></td>");
                                        listado.append(tr);
                                    }


    </script>
</html>