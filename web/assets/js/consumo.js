/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function selectCatSicop() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: pb4,
        error: function (data) {
            alert('error');
        }
    });

}

function pb4(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#selectSicopPicker").append('<option value="' + obj.sicopId + '">' + '➤ ' + obj.sicopDesc + '</option>');

    });
    $('#selectSicopPicker').selectpicker('refresh');

}

function picker() {
    $('#selectSicopPicker').addClass('selectpicker');
    $('#selectSicopPicker').attr('data-live-search', 'true');
}


$(document).ready(function () {
    selectCatSicop();
});


function getReporte() {
    var arti = document.getElementById("selectSicopPicker").value;
    var inicio = document.getElementById("fechaInicio").value;
    var fin = document.getElementById("fechaFinal").value;

    if (inicio < fin) {
        $.ajax({type: "GET",
            url: "api/Consumo/" + arti + "/" + inicio + "/" + fin,
            success: lista
        });
    } else {
        alert("Fecha de incio debe ser menor a la fecha final")
    }

}


function lista(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}

function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sboSicop.sicopDesc + "</td>"
            + "<td>" + objeto.sboSicop.sicopCodiInden + "</td>"
            + "<td>" + objeto.sboSicop.sicopCodiClas + "</td>"
            + "<td>" + objeto.solArtiCant + "</td>");
    listado.append(tr);
}


