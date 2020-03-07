/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function buscarSolicitudesActuales() {
    $.ajax({type: "GET",
        url: "api/subfamilias?filtro=" + $("#filtro").val(),
        success: listaSolicitudesActuales
    });
}

function listaSolicitudesActuales(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        filaActuales(listado, p);
    });
}

function filaActuales(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sicopCodiClas + "</td>"
            + "<td>" + objeto.sicopCodiInden + "</td>"
            + "<td>" + objeto.sicopDesc + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoSicop(\"" + objeto.sicopId + "\");'></td>");
    listado.append(tr);

}