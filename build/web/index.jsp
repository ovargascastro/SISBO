<%-- 
    Document   : index
    Created on : 15-sep-2019, 21:51:05
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="body">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <%@ include file="/presentation/base.jsp" %>
        <title>Página principal</title>
    </head>
    <body>
        <%@ include file="/presentation/header.jsp" %>
        <div id="titulo">
            <div class="jumbotron">
                <a href="index.jsp"></a>
                <h1>Bienvenido al Sistema de Bodega</h1>
            </div>
        </div>

    </body> 
    
</html>
<script>
document.getElementById("index").style.color = "white";
</script>
