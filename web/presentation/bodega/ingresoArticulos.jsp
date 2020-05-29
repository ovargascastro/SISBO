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
    <body onload="selectSicop2(), logged(), cargarSelects()">
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <h1>Ingresar Artículos</h1>
                <p></p>
                <p></p>
            </div>
        </div>
        <form action="javascript:agregarArticulos()">
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
                                    <option values="0" selected disabled = "true" >Seleccione una opción</option>
                                </select>
                            </div>
                            <div class="col">
                                <label>Descripción</label>
                                <input id="AddArtDescripcion" class="form-control" 
                                       type="text" placeholder="Descripción" required>
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
                                            <option value="Gramo">Gramo</option>
                                            <option value="Miligramo">Miligramo</option>
                                            <option value="Litro">Litro</option>
                                            <option value="Mililitro">Mililitro</option>
                                            <option value="Metro">Metro</option>
                                            <option value="Centimetro">Centimetro</option>
                                </select>

                                <label>Precio</label>
                                <input class="form-control" type="number" placeholder="Precio" 
                                       id="Precio" required>

                                <label>Unidad Usuaria</label>
                                <select id="selectDeptos" class="selectpicker form-control" 
                                        data-live-search="true" data-size="15" required>
                                    <option values="0" selected disabled = "true" >Seleccione una opción</option>
                                </select>
                                <label>Fecha de Ingreso</label>
                                <input id="AddArtFIngreso" class="form-control" type="date" 
                                       placeholder="Fecha de Ingreso" required>

                                <label>SICOP</label>
                                <select id="selectSicop" class="selectpicker form-control" 
                                        data-live-search="true" data-size="15" required>
                                    <option values="0" selected disabled = "true" >Seleccione una opción</option>
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
                                    <option values="0" selected disabled = "true">Seleccione una opción</option>
                                </select>

                                <label>Fecha de Vencimiento</label>
                                <input id="AddArtFVencimiento" class="form-control" 
                                       type="date" placeholder="Fecha de Vencimiento">

                                <label>Tipo de Ingreso</label>
                                <select class="form-control" id="selectTipoIng" required>
                                    <option values="0" selected disabled = "true">Seleccione una opción</option>
                                    <option value="Regular" >Regular</option>
                                    <option value="Donacion" >Donación</option>
                                    <option value="Caja Chica">Caja Chica</option>
                                </select> 
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
        <script src="assets/js/departamentos.js" type="text/javascript"></script>
        <script src="assets/js/ingresoArticulosSinOC.js" type="text/javascript"></script>
    </body>

</html>

<script>
        document.getElementById("ArticulosMenu").style.color = "white";

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

        function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
            location.href = "presentation/notAccess.jsp";
    <%}%>
        }

        function cargarSelects() {
            selectBodegas();
            selectDepartamentos();
            selectCatArticulos();
        }

        function selectDepartamentos() {
            $.ajax({type: "GET",
                url: "api/departamentos",
                success: pb4Depto,
                error: function (data) {
                    alert('error');
                }
            });
        }
        
        function pb4Depto(data) {

            var jsonData = JSON.stringify(data);
            $.each(JSON.parse(jsonData), function (idx, obj) {
                $("#selectDeptos").append('<option value="' + obj.deptoIdPk + '">' + '➤ ' + obj.deptoNomb + '</option>');

            });
            $('#selectDeptos').selectpicker('refresh');
        }

        function selectCatArticulos() {
            $.ajax({type: "GET",
                url: "api/catArticulos?filtro=" + " ",
                success: pb4CatArt,
                error: function (data) {
                    alert('error');
                }
            });

        }

        function pb4CatArt(data) {

            var jsonData = JSON.stringify(data);
            $.each(JSON.parse(jsonData), function (idx, obj) {
                $("#selectCatalogoArticulos").append('<option value="' + obj.catIdPk + '">' + '➤ ' + obj.catDesc + '</option>');

            });
            $('#selectCatalogoArticulos').selectpicker('refresh');
        }

        function picker() {
            $('#selectCatalogoArticulos').addClass('selectpicker');
            $('#selectCatalogoArticulos').attr('data-live-search', 'true');
            $('#selectDeptos').addClass('selectpicker');
            $('#selectDeptos').attr('data-live-search', 'true');
        }

</script>
