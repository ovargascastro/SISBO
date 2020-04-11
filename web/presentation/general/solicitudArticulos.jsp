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
        <link rel="stylesheet" href="assets/bootstrap/css/dataTables.bootstrap.min.css">
        
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
 <div class="card" id="tablaCatalogos" style="background-color: rgb(255,255,255);">
        <form>
         
        <input class="form-control" type="hidden" placeholder="departamento" id="departamento"  readonly="readonly">
              <input class="form-control" type="hidden" placeholder="idusuario" id="idusuario"  readonly="readonly">
        <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Marca</th>
                <th>Bodega</th>
                <th>Agregar</th>
            </tr>
        </thead>
        <tbody id="ExistArti">
         
   </tbody>
        <tfoot>
            <tr>
                <th>ID</th>
                <th>Descripción</th>
                <th>Marca</th>
                <th>Bodega</th>
                <th>Agregar</th>
            </tr>
        </tfoot>
    </table>
                 </form>
</div>
        
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
         <script src="assets/js/jquery-3.3.1.js"></script>
        <script src="assets/js/dataTables.bootstrap.min.js"></script>
        <script src="assets/js/jquery.dataTables.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/departamentos.js" type="text/javascript"></script>
        <script src="assets/js/solicitudArticulo.js" type="text/javascript"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </body>

</html>

<script>
 
    document.getElementById("solicitudArtMenu").style.color = "white";
    function cargarSelectsSolArt() {
        // selectDeptos();
         mostrardepa();
        ListaExistencias();
       
        //selecArt();
        depurarLocalStorage();
        mostraridUsuario();
      
    }
    
       function listaExistencias(art) {
        var listado = $("#ExistArti");
        listado.html("");
        art.forEach((a) => {
            filaExistencias(listado, a);
        });
    }
    var array = [];
    var x; 
    function filaExistencias(listado, articulo) {
        var tr = $("<tr />");
        tr.html(
      "<td>" + articulo.id + "</td>"
      + "<td>" + articulo.articulo.sboSicop.sicopDesc  + "</td>"
      + "<td>" + articulo.articulo.artMarc + "</td>"
      + "<td>" + articulo.sboTbBodega.bodeDesc + "</td>"
      + "<td><img src='assets/img/plus.png' onclick='IngresarArticuloLista(\"" + articulo.id +"\",\"" + articulo.articulo.sboSicop.sicopDesc +"\");'></td>");
        listado.append(tr);
    $(document).ready(function() {
    $('#example').DataTable();
} );
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
    //cambiar la funcion de eliminar en la ultima columna
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
    
    
      function generaSolXArti(id,desc){
        var soliXarti = {
            existencia:[{id:id}],
            solArtiDeta: desc
        };
         console.log("generaSOl paso 2" );
        return soliXarti;
       
    }
    
    function IngresarArticuloLista(id,desc){
        
   console.log("IngresaArti paso 1");
   
        insertarLista(generaSolXArti(id,desc));
        agregarSolXArtTabla();
 
    }
    

    function insertarLista(objeto){
          console.log("Inserta paso 3");
       // console.log(comprueba(objeto.existencia));
       if(comprueba(objeto.existencia[0].id)===false){
              window.localStorage.setItem(objeto.existencia[0].id, JSON.stringify(objeto)); 
     swal("Articulo Agregado..!", "Correctamente!!", "success");
   
        }
          else{
                swal("Error!", "Articulo ya ingresado en la solicitud ", "error");
            }
             
        }
  

function comprueba(exist){
    var bandera=false;
    var objeto0=JSON.parse(exist);
   console.log("comprueba paso 4");
    for(var i = 0; i < localStorage.length; i++){
   var objeto1 = JSON.parse(localStorage.key(i));
    console.log(exist);
    console.log(objeto1);
    if(objeto0 === objeto1){
        bandera=true;
        //return true;
       // 
    }

}
   return bandera;
}

    
    function agregarSolXArtTabla(){
        $("#listArt").empty();
        insertaElemento();
    }
    
    function insertaElemento() {
        for (var i = 0; i < localStorage.length; i++){
            var objeto = JSON.parse(localStorage.getItem(localStorage.key(i)));
            var tr = $("<tr id=" + objeto.existencia  + " />");
            tr.html(
                "<td>" + objeto.existencia[0].id + "</td>"
                + "<td>" + objeto.solArtiDeta + "</td>"
                + "<td><img src='assets/img/trash-delete.png' onclick='eliminarArticulo(\"" + objeto.existencia[0].id + "\");'></td>");
            $("#listArt").append(tr);
        }
        //limpiaEspacios();
   }
   
   function creaNuevaSolicitud(){
       creaSolicitud();
   }
   
   function ingresaIdSoli(id){
        for (var i = 0; i < localStorage.length; i++){
            var objeto = JSON.parse(localStorage.getItem(localStorage.key(i)));
             console.log("ingresaid");
             console.log(objeto);
            objeto['sboTbSoliArti'] = [{solArtiIdPk: id}];
            localStorage.setItem(objeto.existencia[0].id, JSON.stringify(objeto));
            
              console.log(id);
            console.log(objeto);
        }
        insertaListaSoliXArti();
   }
   
    function insertaListaSoliXArti(){
        for (var i = 0; i < localStorage.length; i++){
            var objeto = JSON.parse(localStorage.getItem(localStorage.key(i)));
            console.log("insertalista");
             console.log(objeto);
            funcionAuxiliar(objeto);
        }
        limpiartabla();
        
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
          swal("Solicitud creada..!", "Correctamente!!", "success");
    setTimeout(function(){
        window.location = "presentation/general/solicitudes.jsp";
    },2000);
        
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