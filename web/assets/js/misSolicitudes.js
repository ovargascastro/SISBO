/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function buscarSolicitudesActuales() {
    $.ajax({type: "GET",
        url: "api/SolicitudXfuncionario",
        success: listaSolicitudesActuales
    });
}

function buscarSolicitudesTotal() {
    $.ajax({type: "GET",
        url: "api/SolicitudXfuncionario/"+ variableSolActual,
        success: listaSolicitudesTotal
    });
}


function listaSolicitudesTotal(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        filaActuales(listado, p);
    });
}

function listaSolicitudesActuales(personas) {
    var listado = $("#listaPendientes");
    listado.html("");
    personas.forEach((p) => {
        filaActuales(listado, p);
    });
}

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


function cargar() {
    buscarSolicitudesActuales();
    buscarSolicitudesTotal();
}

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

function formatDate(fecha) {
    var dia = fecha.substring(8, 10);
    var mes = fecha.substring(5, 7);
    var annio = fecha.substring(0, 4);
    var newFecha = dia + "/" + mes + "/" + annio;
    return newFecha;
}



function listaArticulosxSol2(personas) {
    var listado = $("#listaArticulosSolicitudPendientes");
    listado.html("");
    personas.forEach((p) => {
        filaArticulos2(listado, p);
    });
}


function filaArticulos2(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sboSicop.sicopDesc + "</td>"
            +"<td>" + objeto.solArtiCant + "</td>");
    listado.append(tr);

}

$(document).ready(function () {
    logged();
});
