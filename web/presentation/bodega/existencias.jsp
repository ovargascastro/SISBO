<!DOCTYPE html>

<html id="body">
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/presentation/base.jsp" %>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <title>Existencias</title>
    </head>

<body style="background-color: rgb(255,255,255);">
        <%@ include file="/presentation/header.jsp" %>
    <div id="titulo">
        <div class="jumbotron">
            <h1>Artículos en Existencia</h1>
            <p></p>
            <p></p>
        </div>
    </div>
    <div class="card" id="cuerpoListaOrdenes">
        <div class="card-body">
            <h4 class="text-center card-title">Existencias</h4>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <form id="existencias" action="javascript:getExistencias()" >
                            <div>
                                <label>Bodega</label>
                                <div class="form-row">
                                    <div class="col">
                                        <select class="form-control" id="SelectBodegas">
                                        </select>
                                    </div>
                                    <div class="col"></div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label>Departamento</label>
                                    <select class="form-control" id="SelectDptos">
                                    </select>
                                    <label>Artículo</label>
                                    <select id="selectSicop" class="form-control" >
                                    </select>
                                </div>
                                <div class="col text-center">
                                    <button class="btn btn-primary" id="btnBuscarExist" type="submit">Buscar</button>
                                </div>
                            </div>
                    </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col"></div>
            </div>
            <div class="row">
                <div class="col text-center" id="tablaOrdenes">
                    <div class="table-responsive">
                        <table class="table" id="myTable">
                            <thead>
                                <tr>
                                    <th class="text-center">Bodega</th>
                                    <th class="text-center">Departamento</th>
                                    <th class="text-center">Descripcion</th>
                                    <th class="text-center">Cantidad</th>
                                </tr>
                            </thead>
                            <tbody id="listadoExistencias">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/existencias.js" type="text/javascript"></script>
</body>

</html>
<script>
    document.getElementById("ArticulosMenu").style.color = "white";


</script>
