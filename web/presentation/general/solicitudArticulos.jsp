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
             <input class="form-control" type="hidden" placeholder="departamento" id="departamento"  readonly="readonly">
              <input class="form-control" type="hidden" placeholder="idusuario" id="idusuario"  readonly="readonly">
        </div>

        <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>Articulo</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
   </tbody>
        <tfoot>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </tfoot>
    </table>
        
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
        
        var cant = document.getElementById("cantidad").value;
        var existencias =  document.getElementById("cantidadExist").value;
        
        if(existencias >= cant){
        insertarLista(generaSolXArti());
        agregarSolXArtTabla();
        }else{
             alert("Cantidad solicitada es mayor a las existencias");
              $("#cantidad").val("");
        }
        
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
   
   function creaNuevaSolicitud(){
       creaSolicitud();
   }
   
   function ingresaIdSoli(id){
        for (var i = 0; i < localStorage.length; i++){
            var objeto = JSON.parse(localStorage.getItem(localStorage.key(i)));
            objeto['sboTbSoliArti'] = [{solArtiIdPk: id}];
            localStorage.setItem(objeto.sboSicop[0].sicopId, JSON.stringify(objeto));
        }
        insertaListaSoliXArti();
   }
   
    function insertaListaSoliXArti(){
        for (var i = 0; i < localStorage.length; i++){
            var objeto = JSON.parse(localStorage.getItem(localStorage.key(i)));
            funcionAuxiliar(objeto);
        }
        limpiartabla();
        limpiaEspacios();
        depurarLocalStorage();
    }
   
    function funcionAuxiliar(objeto){
        $.ajax({type: "POST",
            url: "api/solxArt",
            data: JSON.stringify(objeto),
            contentType: "application/json",
            success: completar,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
    
    function completar(){
        
    }
    
    function limpiartabla(){
        $('#listArt').empty();
    }
    
    function eliminarArticulo(id){
        $('#' + id + '').remove();
        window.localStorage.removeItem(id);
    }
    
    function depurarLocalStorage(){
         window.localStorage.clear(); 
    }
    
    $(document).ready(function () {
    logged();
});
    
    
    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
    
    

  
</script>