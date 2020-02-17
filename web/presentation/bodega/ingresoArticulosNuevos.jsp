<%-- 
    Document   : ingresoArticulosNuevos
    Created on : 15-sep-2019, 21:58:32
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
        <title>Ingreso de Articulos Nuevos</title>
    </head>
    <body onload="selectSicop()">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Ingresar Artículos Nuevos</h1>
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
                                <div><label>Buscar Orden de Compra</label>
                                    <div class="form-row">
                                        <div class="col"><input class="form-control" type="text" placeholder="Codigo Orden de Compra" id="numeroOC" name="numeroOC"></div>
                                        <div class="col"><button class="btn btn-primary" type="button" onclick="buscarOrdenes()">Buscar</button></div>
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
                                            <th class="text-center">Fecha<br>de Solicitud</th>
                                            <th class="text-center">Proveedor</th>
                                            <th class="text-center">Precio<br>Total</th>
                                            <th class="text-center">Estado</th>
                                            <th class="text-center">Artículos</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listadoOC">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" role="dialog" tabindex="-1" id="listaArticulos">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Listado de Artículos</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <div class="container text-center">
                        <div class="row">
                            <div class="col">
                                <div class="table-responsive">
                                    <table class="table" id="ListadoArticulosTotal">
                                        <thead>
                                            <tr>
                                                <th>Artículo</th>
                                                <th>Cantidad Pedida</th>
                                                <th>Cantidad Restante</th>     
                                                <th>Agregar</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listadoOCxArt">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Regresar</button></div>
            </div>
        </div>
    </div>
    <div class="modal fade" role="dialog" tabindex="-1" id="agregarArticulo">
        <div class="modal-dialog" role="document">
            <form id="actualizaArticulo" action="javascript:actualizarArticulo()">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Agregar Artículo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">

                        <div class="container">
                            <div class="form-row">
                                <div class="col">
                                    <input id="AddArtId" class="form-control" type="hidden">
                                    <input id="OCId" class="form-control" type="hidden">
                                    <input id="DptoId" class="form-control" type="hidden">
                                    <label>Artículo</label>
                                    <input id="AddArtArticulo" class="form-control" type="text" readonly placeholder="Artículo">
                                    <label>Descripción</label>
                                    <input id="AddArtDescripcion" class="form-control" type="text" placeholder="Descripcion">
                                    <label>Modelo</label>
                                    <input id="AddArtModelo" class="form-control" type="text" placeholder="Modelo">
                                    <label>Marca</label>
                                    <input id="AddArtMarca" class="form-control" type="text" placeholder="Marca">
                                    <label>N° Serie</label>
                                    <input id="AddArtNSerie" class="form-control" type="text" placeholder="N° Serie">
                                    <label>SICOP</label>
                                    <select class="form-control" id="selectSicop" required></select>
                                </div>                       
                                <div class="col">
                                    <label>Unidad Usuaria</label>
                                    <input id="AddArtUniUsuaria" class="form-control" type="text" readonly placeholder="Unidad Usuaria">    
                                    <label>Bodega</label>
                                    <select class="form-control" id="AddArtBodega" required></select>
                                    <label>Fecha de Ingreso</label>
                                    <input id="AddArtFIngreso" class="form-control" type="date" placeholder="Fecha de Ingreso" required>
                                    <label>Fecha de Vencimiento</label>
                                    <input id="AddArtFVencimiento" class="form-control" type="date" placeholder="Fecha de Vencimiento">
                                    <label>Cantidad a Ingresar</label>
                                    <input id="AddArtCant" class="form-control" type="number" placeholder="Cantidad a Ingresar" required>    
                                    <br>
                                    <div class="col">
                                        <label>Información del Artículo</label>
                                        <div class="text-center" id="botonArticuloInfo"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button>
                        <button class="btn btn-primary" type="submit">Agregar</button></div>
                </div>
            </form>

        </div>
    </div>
    <div class="modal fade" role="dialog" tabindex="-1" id="modalInfoArt">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Información del Artículo</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body">
                    <form>
                        <div class="container">
                            <div class="form-row">
                                <div class="col"><label>Artículo</label><input class="form-control" type="text" id="articuloInfo" readonly><label>Sub-Familia</label><input class="form-control" type="text" id="subfamInfo" readonly></div>
                                <div class="col"><label>Código Artículo</label><input class="form-control" type="text" id="codArtInfo" readonly><label>Familia</label><input class="form-control" type="text" id="famInfo" readonly></div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer"><button class="btn btn-light" type="button" onclick="cerrarInfoArt()" >Cerrar</button></div>
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
    <script src="assets/js/sicop.js" type="text/javascript"></script>
</body>

</html>

<script>
                   document.getElementById("ArticulosMenu").style.color = "white";
                    var articuloActual;
                    function abrirModalListarArticulos(id) {
                        articuloActual = id;
                        buscarArtxOc(id);
                        $('#listaArticulos').modal('show');
                    }
                    function abrirModalAgregarArticulos(idArti) {
                        $('#listaArticulos').modal('hide');
                        solicitarDatosArticulo(idArti);
                        selectBodegas();
                        $('#agregarArticulo').modal('show');
                    }
                    function abrirModalInfoArticulo(idCat) {
                        $('#agregarArticulo').modal('hide');
                        solicitarDatosCatalogosArticulo(idCat);
                        $('#modalInfoArt').modal('show');
                    }
                    function cerrarInfoArt() {
                        $('#agregarArticulo').modal('show');
                        $('#modalInfoArt').modal('hide');
                    }
                    function refrescarListaArticulos(id) {
                        $('#agregarArticulo').modal('hide');
                        $('#listaArticulos').modal('show');
                    }
                    function buscarOrdenes() {
                        $.ajax({type: "GET",
                            url: "api/listadoOCArtNuevos?numeroOC=" + $("#numeroOC").val(),
                            success: listaOC
                        });
                    }
                    function buscarArtxOc(id) {
                        $.ajax({type: "GET",
                            url: "api/ListaOCxArt?numeroOCArt=" + id,
                            success: listaArtxOC
                        });
                    }
                    function solicitarDatosArticulo(id) {
                        $.ajax({type: "GET",
                            url: "api/ListaOCxArt/" + id,
                            success: mostrarDatosArt
                        });
                    }
                    function mostrarDatosArt(objeto) {
                        $("#AddArtId").val(objeto.artIdPk);
                        $("#OCId").val(objeto.sboTbOrdenCompra.ocIdPk);
                        $("#DptoId").val(objeto.abaaTbDepartamento.deptoIdPk);
                        $("#AddArtArticulo").val(objeto.sboTbCatArticulo.catDesc);
                        $("#AddArtDescripcion").val(objeto.artDesc);
                        $("#AddArtModelo").val(objeto.artMode);
                        $("#AddArtMarca").val(objeto.artMarc);
                        $("#AddArtNSerie").val(objeto.artNumeSeri);
                        $("#AddArtUniUsuaria").val(objeto.abaaTbDepartamento.deptoNomb);
                        cargarBotonInfo(objeto.sboTbCatArticulo.catIdPk);
                    }
                    function solicitarDatosCatalogosArticulo(id) {
                        $.ajax({type: "GET",
                            url: "api/descCatsArticulo/" + id,
                            success: mostrarDatosCatsArt
                        });
                    }
                    function mostrarDatosCatsArt(objeto) {
                        $("#codArtInfo").val(objeto.sboTbCatArticulo.catIdPk);
                        $("#articuloInfo").val(objeto.sboTbCatArticulo.catDesc);
                        $("#subfamInfo").val(objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiDesc);
                        $("#famInfo").val(objeto.sboTbCatArticulo.sboTbSubFamilia.sboTbFamilia.famiDesc);
                    }
                    function cargarBotonInfo(catalogoID) {
                        var linea = "<img src='assets/img/info(1).png' onclick='abrirModalInfoArticulo(\"" + catalogoID + "\")'>";
                        $("#botonArticuloInfo").empty().append(linea);
                    }
                    function listaOC(ordenes) {
                        var listado = $("#listadoOC");
                        listado.html("");
                        ordenes.forEach((p) => {
                            filaOC(listado, p);
                        });
                    }
                    function listaArtxOC(ordenes) {
                        var listado = $("#listadoOCxArt");
                        listado.html("");
                        ordenes.forEach((p) => {
                            filaOCxArt(listado, p);
                        });
                    }
                    function formatDate(fecha) {
                        var dia = fecha.substring(8, 10);
                        var mes = fecha.substring(5, 7);
                        var annio = fecha.substring(0, 4);
                        var newFecha = dia + "/" + mes + "/" + annio;
                        return newFecha;
                    }
                    function filaOC(listado, objeto) {
                        var tr = $("<tr />");
                        tr.html(
                                "<td>" + objeto.ocIdPk + "</td>"
                                + "<td>" + formatDate(objeto.ocFecha) + "</td>"
                                + "<td>" + objeto.abaaTbProveedor.proveNomb + "</td>"
                                + "<td>" + objeto.ocPrecTota + "</td>"
                                + "<td>" + objeto.ocEsta + "</td>"
                                + "<td><img src='assets/img/delivery-cart.png' onclick='abrirModalListarArticulos(\"" + objeto.ocIdPk + "\");'></td>");
                        listado.append(tr);
                    }
                    function filaOCxArt(listado, objeto) {
                        var tr = $("<tr />");
                        tr.html(
                                "<td>" + objeto.artDesc + "</td>"
                                + "<td>" + objeto.artCant + "</td>"
                                + "<td>" + objeto.artCantRest + "</td>"
                                + "<td><img class='small-img' src='assets/img/plus.png' onclick='abrirModalAgregarArticulos(\"" + objeto.artIdPk + "\");'></td>");
                        listado.append(tr);
                    }
                    function selectBodegas() {
                        var vacio = "";
                        $.ajax({type: "GET",
                            url: "api/BodegaListaOC?filtro=" + vacio,
                            success: function (data) {
                                $.each(data, function (key, bod) {
                                    $("#AddArtBodega").append('<option value=' + bod.bodeIdPk + '>' + bod.bodeIdPk + ' - ' + bod.bodeDesc + '</option>');
                                });
                            },
                            error: function (data) {
                                alert('error');
                            }
                        });
                    }
                    function parseaFecha(fechaOriginal) {
                        var fecha = fechaOriginal;
                        var fecha2;
                        if (fecha.length > 0) {
                            fecha2 = fecha.toDate("yyyy-mm-dd");
                        } else {
                            fecha2 = null;
                        }
                        return fecha2;
                    }
                    function actualizarArticulo() {
                        aumentarExistencias();
                        disminuirRestantes();
                        $("#actualizaArticulo").trigger('reset');
                    }
                    function aumentarExistencias() {
                        existencia = {
                            sboTbBodega: [{bodeIdPk: $("#AddArtBodega").val()}],
                            abaaTbDepartamento: [{deptoIdPk: $("#DptoId").val()}],
                            sboTbSicop: [{sicopId: $("#selectSicop").val()}], 
                            exisCant: $("#AddArtCant").val()
                        };
                        $.ajax({type: "PUT",
                            url: "api/Existencias",
                            data: JSON.stringify(existencia),
                            contentType: "application/json"});
                    }
                    function disminuirRestantes() {
                        articulo = {
                            artIdPk: $("#AddArtId").val(),
                            artDesc: $("#AddArtDescripcion").val(),
                            artMode: $("#AddArtModelo").val(),
                            artMarc: $("#AddArtMarca").val(),
                            artNumeSeri: $("#AddArtNSerie").val(),
                            artFingr: parseaFecha($("#AddArtFIngreso").val()),
                            artFvenc: parseaFecha($("#AddArtFVencimiento").val()),
                            sboSicop:[{sicopId:$("#selectSicop").val()}],
                            sboTbOrdenCompra: [{ocIdPk: $("#OCId").val()}],
                            artCantRest: $("#AddArtCant").val()
                        };
                        $.ajax({type: "PUT",
                            url: "api/ListaOCxArt",
                            data: JSON.stringify(articulo),
                            contentType: "application/json"})
                                .then(function () {
                                    buscarArtxOc($("#OCId").val());
                                    buscarOrdenes();
                                });
                        refresca();
                    }
                    function refresca() {
                        $('#agregarArticulo').modal('hide');
                        $('#listadoOCxArt').empty();
                        buscarArtxOc(articuloActual);
                        $('#listaArticulos').modal('show');
                    }
                    String.prototype.toDate = function (format)
                    {
                        var normalized = this.replace(/[^a-zA-Z0-9]/g, '-');
                        var normalizedFormat = format.toLowerCase().replace(/[^a-zA-Z0-9]/g, '-');
                        var formatItems = normalizedFormat.split('-');
                        var dateItems = normalized.split('-');
                        var monthIndex = formatItems.indexOf("mm");
                        var dayIndex = formatItems.indexOf("dd");
                        var yearIndex = formatItems.indexOf("yyyy");
                        var today = new Date();
                        var year = yearIndex > -1 ? dateItems[yearIndex] : today.getFullYear();
                        var month = monthIndex > -1 ? dateItems[monthIndex] - 1 : today.getMonth() - 1;
                        var day = dayIndex > -1 ? dateItems[dayIndex] : today.getDate();
                        return new Date(year, month, day);
                    };


</script>
