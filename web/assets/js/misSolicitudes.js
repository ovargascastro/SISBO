/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//se listan las solicitudes pendientes que corresponden a un funcionario
function buscarSolicitudesActuales() {
    $.ajax({type: "GET",
        url: "api/SolicitudXfuncionario",
        success: listaSolicitudesActuales
    });
}
//se listan todas las solicitudes  que corresponden a un funcionario

function buscarSolicitudesTotal() {
    $.ajax({type: "GET",
        url: "api/SolicitudXfuncionario/"+ variableSolActual,
        success: listaSolicitudesTotal
    });
}

//se muestran todas las solicitudes en una tabla
function listaSolicitudesTotal(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        filaActuales(listado, p);
    });
}
//se muestran  las solicitudes pendientes en una tabla
function listaSolicitudesActuales(personas) {
    var listado = $("#listaPendientes");
    listado.html("");
    personas.forEach((p) => {
        filaActuales(listado, p);
    });
}
//se utliza para mostrar por fila las solicitudes
function filaActuales(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.solArtiIdPk + "</td>"
            + "<td>" + formatDate(objeto.solArtiFechSoli) + "</td>"
            + "<td>" + objeto.solArtiEsta + "</td>"
            + "<td><img src='assets/img/delivery-cart.png' onclick='articulosXSolicitud2(\"" + objeto.solArtiIdPk + "\");'></td>");
    listado.append(tr);

}

window.addEventListener('load', cargar, false);

//se cargan los datos al ingresar al url de solicitues de funcionario
function cargar() {
    buscarSolicitudesActuales();
    buscarSolicitudesTotal();
}
//se muestran los articulos qeu pertenecen a una solicitud
var variableSolActual = 0;
function articulosXSolicitud2(id) {
    variableSolActual = id;
    $.ajax({type: "GET",
        url: "api/pendientesXfunc?filtro=" + variableSolActual,
        success: listaArticulosxSol2,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }

    });
    $('#listaArtxSol').modal('show');
}
//darle el formato correcto a las fechas
function formatDate(fecha) {
    var dia = fecha.substring(8, 10);
    var mes = fecha.substring(5, 7);
    var annio = fecha.substring(0, 4);
    var newFecha = dia + "/" + mes + "/" + annio;
    return newFecha;
}


// se muestran en una tabla los articulos que pertencen a la solicitud
function listaArticulosxSol2(personas) {
    var listado = $("#listaArticulosSolicitudPendientes");
    listado.html("");
    personas.forEach((p) => {
        filaArticulos2(listado, p);
    });
}

//se utliza para llenar las filas de la tabla de la funcion anterior
function filaArticulos2(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sboSicop.sicopDesc + "</td>"
            +"<td>" + objeto.solArtiCant + "</td>");
    listado.append(tr);

}
//funcion para vrificar los datos de la sesion
$(document).ready(function () {
    logged();
});