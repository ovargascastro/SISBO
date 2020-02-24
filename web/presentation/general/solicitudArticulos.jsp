<%-- 
    Document   : solicitudArticulos
    Created on : 03/10/2019, 10:39:59 PM
    Author     : oscar
--%>
<%@page import="logic.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html id="body">
    
    
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Solicitud de Articulos</title>
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
    </head>
    <body style="background-color: rgb(255,255,255);" onload="cargarSelectsSolArt()">
       
        <%@ include file="/presentation/header.jsp" %>
  
        <div id="titulo">
            <div class="jumbotron">
                <h1>Solicitud de Artículos</h1>
                <p>Rellenar el formulario para realizar una solicitud de artículos en bodega</p>
                <p></p>
            </div>
        </div>

        <form id="formSolicitudArt" action="javascript:IngresarArticuloLista()">
            <div class="card" id="formulario">
                <div class="card-body">
                    <h5 class="text-center">Seleccione el artículo y la cantidad deseada</h5>
                    <div class="form-row">
                        
                          
                              
                                <input class="form-control" type="hidden" placeholder="departamento" id="departamento"  readonly="readonly">
                                <input class="form-control" type="hidden" placeholder="idusuario" id="idusuario"  readonly="readonly">

                         
                            <div class="col">
                                <label>Artículo</label>
                                <select class="form-control" id="selectArt" onchange="getExistencias()" required>
                                    <option values="0" selected disabled = "true">Seleccione una opcion</option>
                                </select>
                            </div>
                             <div class="col">
                                <label>Descripción</label>
                                 <input class="form-control" type="text" placeholder="Descripción" id="descripcion">
                            </div>
                        
                            <div class="col">
                                <label>Existencias</label>
                                <input class="form-control" type="text" placeholder="Existencias" readonly="readonly" id="cantidadExist">
                            </div>
                            <div class="col">
                                <label>Cantidad</label>
                                <input class="form-control" type="number" placeholder="Cantidad" id="cantidad" required>
                            </div>
                        </div>
                        <div class="form-row text-center" id="rowBtnAgregar">
                            <br>
                            <div class="col">
                                <button class="btn btn-primary text-center" id="btnAgregarArt" type="submit" >Agregar Articulo</button>
                            </div>
                        </div>
                </div>
            </div>
        </form>
        <form action="javascript:creaSolicitud()">
            <div class="card" id="formulario">
                <div class="card-body">
                    
                    <h5 class="text-center">Artículos a solicitar</h5>
                    
                    <div class="container" id="contenedorEncabezado">

                        <div class="form-row">
                            <div class="col text-center">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Artículo</th>
                                                <th>Descripción</th>
                                                <th>Cantidad</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center" id="listArt">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="form-row" id="linea">
                            <div class="col">
                                <hr>
                            </div>
                        </div>
                        <div class="form-row text-center" id="botonGuardar">
                            <div class="col">
                                <button class="btn btn-success" type="submit">Solicitar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/departamentos.js" type="text/javascript"></script>
        <script src="assets/js/solicitudArticulo.js" type="text/javascript"></script>
    </body>

</html>

<script>
   document.getElementById("solicitudArtMenu").style.color = "white";
    function cargarSelectsSolArt() {
        // selectDeptos();
        mostrardepa();
        selecArt();
        depurarLocalStorage();
        mostraridUsuario();
    }

    function listaArtTemp(art) {
        var listado = $("#listArt");
        listado.html("");
        art.forEach((a) => {
            filaArtTemp(listado, a);
        });
        limpiaEspacios();
    }

    var array = [];
    var x;
    function filaArtTemp(listado, articulo) {
        var tr = $("<tr />");
        tr.html(
            "<td>" + articulo.artDesc + "</td>"
            + "<td>" + articulo.cantSolArt + "</td>"
            + "<td><img src='assets/img/trash-delete.png' onclick='eliminaArt(\"" + articulo.artIdPk + "\");'></td>");
        listado.append(tr);
    }

    function limpiaEspacios() {
        $('#formSolicitudArt').trigger("reset");
        mostrardepa();
    }
    
    function mostrardepa(){
        var depa=${logged.getDepartamento().getDeptoIdPk()};
        $("#departamento").val(depa);    
    }
      function mostraridUsuario(){
        var usu=${logged.getPersIdPK()};
        $("#idusuario").val(usu);    
    }
    
    function IngresarArticuloLista(){
        insertarLista(generaSolXArti());
        agregarSolXArtTabla();
    }
    
    function generaSolXArti(){
        var soliXarti = {
            sboSicop: [{sicopId: $("#selectArt").val(), sicopDesc: $( "#selectArt option:selected").text()}],
            solArtiCant: $("#cantidad").val(),
            solArtiDeta: $("#descripcion").val()
        };
        return soliXarti;
    }
    
    function insertarLista(objeto){
        window.localStorage.setItem($("#selectArt").val(), JSON.stringify(objeto));
    }
    
    function agregarSolXArtTabla(){
        $("#listArt").empty();
        insertaElemento();
    }
    
    function insertaElemento() {
        for (var i = 0; i < localStorage.length; i++){
            var objeto = JSON.parse(localStorage.getItem(localStorage.key(i)));
            var tr = $("<tr id=" + objeto.sboSicop[0].sicopId + " />");
            tr.html(
                "<td>" + objeto.sboSicop[0].sicopDesc + "</td>"
                + "<td>" + objeto.solArtiDeta + "</td>"
                + "<td>" + objeto.solArtiCant + "</td>"
                + "<td><img src='assets/img/trash-delete.png' onclick='eliminarArticulo(\"" + objeto.sboSicop[0].sicopId + "\");'></td>");
            $("#listArt").append(tr);
        }
        limpiaEspacios();
   }
    
    function eliminarArticulo(id){
        $('#' + id + '').remove();
        window.localStorage.removeItem(id);
    }
    
    function depurarLocalStorage(){
         window.localStorage.clear(); 
    }

</script>
