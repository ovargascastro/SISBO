<%-- 
    Document   : administracionCatalogos
    Created on : 15-sep-2019, 22:03:11
    Author     : oscar
--%>
<%-- 
    Document   : administracionCatalogos
    Created on : 15-sep-2019, 22:03:11
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Administración de Catalogos</title>
    </head>
    <body onload="javascript:cargarSelects()">
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
                                    <div class="col text-center"><select class="form-control" id="selectcatalogos"><optgroup label="Catalogos"><option value="1" selected="">Familia</option><option value="2">Sub-Familia</option><option value="3">Artículo</option><option value="4">Contables</option></optgroup></select>
                                        <div class="form-row">
                                            <div class="col"><br><input class="form-control" type="text" id="filtro" placeholder="Filtro"></div>
                                        </div>
                                    </div>
                                    <div class="col text-left"><button class="btn btn-primary border-light" type="button" onclick="javascript:concatenarBusqueda()">Buscar &nbsp;<img id="magnifier" src="assets/img/magnifier.png"></button></div>
                                </div>
                            </form>
                        </div>
                        <div class="col">
                            <h4>Agregar Registro a un catálogo</h4><br><br><button class="btn btn-primary" type="button" onclick="javascript:agregarACatalogo()">Registrar</button></div>
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
                                                <th>Estado</th>
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


        <div role="dialog" tabindex="-1" class="modal fade" id="modalDesactivar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Desactivar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Desea desactivar el registro seleccionado?</p>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button class="btn btn-primary bg-danger" type="button" onclick="javascript:Desactivar()">Desactivar</button></div>
                </div>
            </div>
        </div>
        
                <div role="dialog" tabindex="-1" class="modal fade" id="modalActivar">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Activar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <p>Desea activar el registro seleccionado?</p>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button class="btn btn-primary bg-success" type="button" onclick="javascript:Activar()">Activar</button></div>
                </div>
            </div>
        </div>

        <div class="modal fade" role="dialog" tabindex="-1" id="modalEditarFam">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="javascript:actualizarFamilia()">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Código Fam<br></label><input class="form-control" type="text" id="codigoFamilia" readonly></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripFamilia" required></div>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button><button class="btn btn-primary" type="submit">Guardar</button></div>
                    </form>

                </div>
            </div>
        </div>

        <div class="modal fade" role="dialog" tabindex="-1" id="modalEditarCatContable">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="javascript:actualizarCatContable()">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Código Contable<br></label><input class="form-control" type="text" id="codigoContable" readonly></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Nivel<br></label><input class="form-control" type="text" id="NivelContable"></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripContable" required></div>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Close</button><button class="btn btn-primary" type="submit">Guardar</button></div>
                    </form>

                </div>
            </div>
        </div>
        
        
        
        <div class="modal fade" role="dialog" tabindex="-1" id="modalSubFam">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="javascript:actualizarSubFamilia()">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Código Sub-Fam<br></label><input class="form-control" type="text" id="codigoSubFam" readonly></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripSubFam" required></div>
                                </div>
                                <div class="form-row">

                                    <div class="col"><label>CodFamilia<br></label><select class="form-control" id="selectFamilias" required></select></div>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Guardar</button></div>
                    </form>

                </div>
            </div>
        </div>


        <div class="modal fade" role="dialog" tabindex="-1" id="modalArticulo">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="javascript:actualizarCatArticulo()">
                    <div class="modal-header">
                        <h4 class="modal-title">Editar</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        
                            <div class="container">
                                <div class="form-row">
                                    <div class="col"><label>Descripción<br></label><input class="form-control" type="text" id="descripArt" required=""></div>
                                </div>
                                <div class="form-row">
                                    <div class="col"><label>Sub-Familia<br></label>
                                        <select class="form-control" id="selectSubFam" required></select></div>
                                </div>
                            </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Guardar</button></div>
                   </form>

                </div>
            </div>
        </div>
        
         <div class="modal fade" role="dialog" tabindex="-1" id="modalAgregarCatArticulo">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="javascript:CrearCatArticulo()">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar Artículo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <div class="container">
                        
                            <div class="form-row">
                                <div class="col"><label>Descripción</label><input class="form-control" type="text" id="AgregarDescipcionCatArt" required></div>
                                </div>
                             <div class="form-row">
                                <div class="col"><label>Código Articulo Sicop</label><input class="form-control" type="text" id="AgregarCodSicopCatArt" required></div>
                                </div>
                             <div class="form-row">
                                <div class="col"><label>SubFamilia</label><select class="form-control" id="AgregarSubfamiliaCatArt"></select></div>
                            </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Agregar</button></div>
                </form>

            </div>
        </div>
    </div>
        
        <div class="modal fade" role="dialog" tabindex="-1" id="modalAgregarCatContable">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="javascript:crearCatContable()">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar código contable </h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <div class="container">
                         <div class="form-row">
                                <div class="col"><label>Código Contable</label><input class="form-control" type="text" id="AgregarCodCatCont" required></div>
                                </div>
                            <div class="form-row">
                                <div class="col"><label>Descripción</label><input class="form-control" type="text" id="AgregarDescipcionCatCont" required></div>
                                </div>
                             <div class="form-row">
                                <div class="col"><label>Nivel</label><input class="form-control" type="text" id="AgregarNivelCatCont" required></div>
                                </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Agregar</button></div>
                </form>

            </div>
        </div>
    </div>
        
        
         <div class="modal fade" role="dialog" tabindex="-1" id="modalAgregarSubFamilia">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="javascript:crearSubFamilia()">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar SubFamilia</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <div class="container">
                        
                            <div class="form-row">
                                <div class="col"><label>Código</label><input class="form-control" type="text" id="AgregarCodigoSubF" required></div>
                                </div>
                             <div class="form-row">
                                <div class="col"><label>Descripción</label><input class="form-control" type="text" id="AgregarDescripcionSubF" required></div>
                                </div>
                             <div class="form-row">
                                <div class="col"><label>Familia</label><select class="form-control" id="AgregarFamiliaSubF" required></select></div>
                            </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Agregar</button></div>
               </form>

            </div>
        </div>
    </div>
        
    <div class="modal fade" role="dialog" tabindex="-1" id="modalAgregarFamilia">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="javascript:crearFamilia()">
                <div class="modal-header">
                    <h4 class="modal-title">Agregar Familia</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <div class="container">
                        
                            <div class="form-row">
                                <div class="col"><label>Código</label><input class="form-control" type="text" id="AgregarCodigoFam" required></div>
                                </div>
                             <div class="form-row">
                                <div class="col"><label>Descripción</label><input class="form-control" type="text" id="AgregarDescripcionFam" required></div>
                               
                            </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button><button class="btn btn-primary" type="submit">Agregar</button></div>
                </form>
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
                                        if(objeto.famiEstado === '0'){
                                             tr.html(
                                                "<td>" + objeto.famiIdPk + "</td>"
                                                + "<td>" + objeto.famiDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.famiIdPk + "\");'></td>"
                                                +"<td><img src='assets/img/lock.png' onclick='abrirModalDesactivar(\"" + objeto.famiIdPk + "\");'></td>");
                                         listado.append(tr); 
                                        }else{
                                             tr.html(
                                                "<td>" + objeto.famiIdPk + "</td>"
                                                + "<td>" + objeto.famiDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.famiIdPk + "\");'></td>"
                                                +"<td><img src='assets/img/unlock.png' onclick='abrirModalDesactivar(\"" + objeto.famiIdPk + "\");'></td>");       
                                        listado.append(tr);
                                        }
                                       
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
    if (objeto.subFamiEstado === '0') {
        tr.html(
                "<td>" + objeto.subFamiIdPk + "</td>"
                + "<td>" + objeto.subFamiDesc + "</td>"
                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.subFamiIdPk + "\");'></td>"
                + "<td><img src='assets/img/lock.png' onclick='abrirModalDesactivar(\"" + objeto.subFamiIdPk + "\");'></td>");
        listado.append(tr);
    } else {
        tr.html(
                "<td>" + objeto.subFamiIdPk + "</td>"
                + "<td>" + objeto.subFamiDesc + "</td>"
                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.subFamiIdPk + "\");'></td>"
                + "<td><img src='assets/img/unlock.png' onclick='abrirModalDesactivar(\"" + objeto.subFamiIdPk + "\");'></td>");
        listado.append(tr);

    }
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
                                        if(objeto.artCat_Estado==='0'){
                                        tr.html(
                                                "<td>" + objeto.catIdPk + "</td>"
                                                + "<td>" + objeto.catDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.catIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/lock.png' onclick='abrirModalDesactivar(\"" + objeto.catIdPk + "\");'></td>");
                                        listado.append(tr);
                                    }else{
                                        tr.html(
                                                "<td>" + objeto.catIdPk + "</td>"
                                                + "<td>" + objeto.catDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.catIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/unlock.png' onclick='abrirModalDesactivar(\"" + objeto.catIdPk + "\");'></td>");
                                        listado.append(tr);
                                        
                                        
                                    }
                                    }


                                    function listaCatConta(personas) {
                                        var listado = $("#listado");
                                        listado.html("");
                                        personas.forEach((p) => {
                                            filaCatConta(listado, p);
                                        });
                                    }

                                    function filaCatConta(listado, objeto) {
                                        var tr = $("<tr />");
                                        if(objeto.cntEst==='0'){
                                        tr.html(
                                                "<td>" + objeto.cntCodi + "</td>"
                                                + "<td>" + objeto.cntDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.cntIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/lock.png' onclick='abrirModalDesactivar(\"" + objeto.cntIdPk + "\");'></td>");
                                        listado.append(tr);
                                    }else{
                                        tr.html(
                                                "<td>" + objeto.cntCodi + "</td>"
                                                + "<td>" + objeto.cntDesc + "</td>"
                                                + "<td><img class='small-img' src='assets/img/edit.png' onclick='abrirModalEditar(\"" + objeto.cntIdPk + "\");'></td>"
                                                + "<td><img src='assets/img/unlock.png' onclick='abrirModalDesactivar(\"" + objeto.cntIdPk + "\");'></td>");
                                        listado.append(tr);
                                        
                                        
                                    }
                                    }

    </script>
</html>
