<%-- 
    Document   : proveedores
    Created on : 24/01/2020, 12:36:16 PM
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Catalogo de Proveedores</title>
    </head>

<body style="background-color: rgb(255,255,255);" onload="javascript:buscarProvs()">
           <%@ include file="/presentation/header.jsp" %>
    <div id="titulo">
        <div class="jumbotron">
            <h1>Catálogo de Proveedores</h1>
            <p></p>
            <p></p>
        </div>
    </div>
   
        <div class="card" id="formulario">
            <div class="card-body">
                <h4 class="text-center">Proveedores</h4>
                <div class="container" id="contenedorEncabezado">
                    <div class="form-row">
                         <form id="busqueda" action="javascript:buscarProvs()">
                        <div class="col">
                            <div class="form-row">
                                <div class="col">
                                    <label class="col-form-label">Buscar</label>
                                </div>
                            </div>
                            <input id="filtro" class="form-control" type="text" placeholder="Nombre de Proveedor" onkeyup="myFunction()">
                        </div>

                         </form>
                    </div>
                    <div class="form-row text-center" id="rowBtnAgregar">
                        <div class="col"><button class="btn btn-primary" type="button" onclick="mostrarModalInfoProvAgregar()">Registrar</button></div>
                    </div>
                    <div class="form-row" id="linea">

                    </div>
                    <div class="form-row">
                        <div class="col text-center">
                            <div class="table-responsive">
                                <table class="table" id="myTable">
                                    <thead>
                                        <tr>
                                            <th>Código</th>
                                            <th>Nombre</th>
                                            <th>Info</th>
                                        </tr>
                                    </thead>
                                    <tbody id="listado">
                                        
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
                </div>
            </div>
        </div>
    
    <form id="actualizaProv" action="javascript:actualizarProveedor()">
                <div class="modal fade" role="dialog" tabindex="-1" id="modalProveedor">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Proveedor</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="form-row">
                                <div class="col"><label>Nombre</label><input class="form-control" type="text" id="nombProv" placeholder="Nombre"></div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label>Código</label><input class="form-control" type="text" placeholder="Codigo Proveedor" id="codProv">
                                    <label>Teléfono</label><input class="form-control" type="text" placeholder="Telefono" id="telProv">
                                    <label>Fax</label><input class="form-control" type="text" placeholder="Fax" id="faxProv"></div>
                                <div class="col">
                                    <label>Cédula</label><input class="form-control" type="text" placeholder="Cedula" id="cedProv">
                                    <label>Correo</label><input class="form-control" type="text" placeholder="Correo" id="correoProv"></div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button>
                        <button class="btn btn-success" type="submit">Guardar</button></div>
                </div>
            </div>
        </div>
    </form>
    
    
    
        <form id="agregarProv" action="javascript:agregarProveedor()">
                <div class="modal fade" role="dialog" tabindex="-1" id="modalProveedorAgreg">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Proveedor</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="form-row">
                                <div class="col"><label>Nombre</label><input class="form-control" type="text" id="nombProv2" placeholder="Nombre" required></div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label>Código</label><input class="form-control" type="text" placeholder="Codigo Proveedor" id="codProv2" required>
                                    <label>Teléfono</label><input class="form-control" type="text" placeholder="Telefono" id="telProv2">
                                    <label>Fax</label><input class="form-control" type="text" placeholder="Fax" id="faxProv2"></div>
                                <div class="col">
                                    <label>Cédula</label><input class="form-control" type="text" placeholder="Cedula" id="cedProv2">
                                    <label>Correo</label><input class="form-control" type="text" placeholder="Correo" id="correoProv2"></div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cerrar</button>
                        <button class="btn btn-success" type="submit">Registrar</button></div>
                </div>
            </div>
        </div>
    </form>

    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/proveedores.js" type="text/javascript"></script>
</body>

</html>
<script>
     function listaProvs(personas){
        var listado=$("#listado");
        listado.html("");
        personas.forEach( (p)=>{
          fila(listado,p);
        });
    }
        
    function fila(listado,proveedor){
        var tr =$("<tr />");
        tr.html(
            "<td>"+ proveedor.proveCodigo +"</td>"  
            +"<td>"+ proveedor.proveNomb +"</td>"
            +"<td align='center' ><img src='assets/img/edit.png' onclick='infoProveedor(\""+proveedor.proveIdProvePk+"\");'></td>");
        listado.append(tr);
    }
    
    
    function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filtro");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function ocultarModalInfoProv() {
    $('#busqueda').trigger("reset");
    $('#actualizaProv').trigger("reset");
    $('#modalProveedor').modal('hide');
    buscarProvs();
    
}

    function logged() {
    <% AbaaTbPersona aux = (AbaaTbPersona) session.getAttribute("logged");%>
    <% if (aux == null || !aux.getDepartamento().getDeptoIdPk().equals("17") && !aux.getDepartamento().getDeptoIdPk().equals("5")) { %>
        location.href = "presentation/notAccess.jsp";
    <%}%>
    }
     document.getElementById("CatalogosMenu").style.color = "white";
</script>