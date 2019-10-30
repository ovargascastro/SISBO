<!DOCTYPE html>
<html id="body">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Existencias</title>
        <base href="http://localhost:8084/SISBO/" >
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
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
                        <form>
                            <div><label>Departamento</label>
                                <div class="form-row">
                                    <div class="col"><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                                    <div
                                        class="col"><button class="btn btn-primary" type="button">Búscar</button></div>
                            </div>
                            <div class="form-row">
                                <div class="col"><label>Artículo</label><select class="form-control"><optgroup label="This is a group"><option value="12" selected="">This is item 1</option><option value="13">This is item 2</option><option value="14">This is item 3</option></optgroup></select></div>
                                <div
                                    class="col"></div>
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
                                <th class="text-center">Bodega</th>
                                <th class="text-center">Artículo</th>
                                <th class="text-center">Cantidad</th>
                                <th class="text-center">Aumentar</th>
                                <th class="text-center">Disminuir</th>
                            </tr>
                        </thead>
                        <tbody>
                            
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
</body>

</html>
<script>
    document.getElementById("ArticulosMenu").style.color = "white";
    </script>