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

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

        <!-- (Optional) Latest compiled and minified JavaScript translation files -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/i18n/defaults-*.min.js"></script>
        <title>Ingreso de Articulos</title>
    </head>
    <body onload="selectSicop(), logged(), cargarSelects()">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Ingresar Artículos</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <form action="javascript:agregarArt()">
            <div class="card" id="formulario">
                <div class="card-body">
                    <h4 class="text-center">Ingreso de Artículos</h4>
                    <div class="container" id="contenedorEncabezado">
                        <div class="form-row">
                            <div class="col">
                                <input id="AddArtId" class="form-control" type="hidden">
                                <input id="OCId" class="form-control" type="hidden">
                                <input id="DptoId" class="form-control" type="hidden">
                                <label>Artículo</label>
                                <select id="selectCatalogoArticulos" class="selectpicker form-control" 
                                        data-live-search="true" data-size="15" required>
                                    <option values="0" selected disabled = "true" >Seleccione una opcion</option>
                                </select>
                                <style>
                                    div.dropdown-menu.open { width: 100%; }
                                    ul.dropdown-menu.inner>li>a { white-space: initial; }
                                </style>
                            </div>
                            <div class="col">
                                <label>Descripción</label>
                                <input id="AddArtDescripcion" class="form-control" 
                                       type="text" placeholder="Descripcion" required>
                            </div>
                        </div>

                        <div class="form-row">

                            <div class="col">
                                <label>Modelo</label>
                                <input id="AddArtModelo" class="form-control" type="text" placeholder="Modelo">

                                <label>Unidad de Medida</label>
                                <select class="form-control" id="selectUnidadMedida" required>
                                    <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                    <option value="Unidad" >Unidad</option>
                                    <option value="Kilo">Kilo</option>
                                    <option value="Metro">Metro</option>
                                </select>

                                <label>Precio</label>
                                <input class="form-control" type="number" placeholder="Precio" 
                                       id="Precio" required>

                                <label>Unidad Usuaria</label>
                                <select class="form-control" id="selectDeptos" required>
                                    <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                </select>  

                                <label>Fecha de Ingreso</label>
                                <input id="AddArtFIngreso" class="form-control" type="date" 
                                       placeholder="Fecha de Ingreso" required>

                                <label>SICOP</label>
                                <select class="form-control" id="selectSicop" required>
                                    <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                </select>

                            </div>

                            <div class="col">
                                <label>Marca</label>
                                <input id="AddArtMarca" class="form-control" type="text" placeholder="Marca">

                                <label>N° Serie</label>
                                <input id="AddArtNSerie" class="form-control" type="text" placeholder="N° Serie">

                                <label>Cantidad a Ingresar</label>
                                <input id="AddArtCant" class="form-control" 
                                       type="number" placeholder="Cantidad a Ingresar" min="0" required> 

                                <label>Bodega</label>
                                <select class="form-control" id="AddArtBodega" required> 
                                    <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                </select>

                                <label>Fecha de Vencimiento</label>
                                <input id="AddArtFVencimiento" class="form-control" 
                                       type="date" placeholder="Fecha de Vencimiento">
                            </div>

                        </div>
                        <div class="form-row" id="linea">
                            <div class="col">
                                <hr>
                            </div>
                        </div>
                        <div class="form-row text-center" id="botonGuardar">
                            <div class="col"><button class="btn btn-success" type="submit">Guardar</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </form>   
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

        <script src="assets/js/sicop.js" type="text/javascript"></script>
        <script src="assets/js/proveedores.js" type="text/javascript"></script>
        <script src="assets/js/departamentos.js" type="text/javascript"></script>
        <script src="assets/js/ingresoArticulos.js" type="text/javascript"></script>
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
            $('#buscarOrdArt').trigger("reset");
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
                sboSicop: [{sicopId: $("#selectSicop").val()}],
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

        function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
     <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
            location.href = "presentation/notAccess.jsp";
    <%}%>
        }

        function cargarSelects() {
            selectBodegas();
            selectProveedores();
            selectDeptos();
            selectCatArticulos();
        }

        function selectCatArticulos() {
            $.ajax({type: "GET",
                url: "api/catArticulos?filtro=" + " ",
                success: pb4,
                error: function (data) {
                    alert('error');
                }
            });

        }

        function pb4(data) {

            var jsonData = JSON.stringify(data);
            $.each(JSON.parse(jsonData), function (idx, obj) {
                $("#selectCatalogoArticulos").append('<option value="' + obj.catIdPk + '">' + '➤ ' + obj.catDesc + '</option>');

            });
            $('#selectCatalogoArticulos').selectpicker('refresh');

        }

        function picker() {
            $('#selectCatalogoArticulos').addClass('selectpicker');
            $('#selectCatalogoArticulos').attr('data-live-search', 'true');
        }
        




</script>
