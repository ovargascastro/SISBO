/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// se listan los datos del catalogo de sicop
function selectCatSicop() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: pb4,
        error: function (data) {
            alert('error');
        }
    });

}
//funcion para darle formato a los datos en el select
function pb4(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#selectSicopPicker").append('<option value="' + obj.sicopId + '">' + '➤ ' + obj.sicopDesc + '</option>');

    });
    $('#selectSicopPicker').selectpicker('refresh');

}



function selectDeptos() {
    $.ajax({type: "GET",
       url: "api/departamentos",
        success: cargarDeptos,
        error: function (data) {
            alert('error');
        }
    });

}

function cargarDeptos(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#selectDptoPicker").append('<option value="' + obj.deptoIdPk + '">' + '➤ ' + obj.deptoNomb + '</option>');

    });
    $('#selectDptoPicker').selectpicker('refresh');

}

function picker() {
    $('#selectSicopPicker').addClass('selectpicker');
    $('#selectSicopPicker').attr('data-live-search', 'true');
}


$(document).ready(function () {
    selectCatSicop();
    selectDeptos();
});

//se muestrann los datos solicitados en el reporte
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

//se lista en la tabla los articulos de sicop
function lista(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}
//se utiliza para llenar las filas de la funcion anterior
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.existencia.articulo.sboSicop.sicopDesc + "</td>"
            + "<td>" + objeto.existencia.articulo.sboSicop.sicopCodiInden + "</td>"
            + "<td>" + objeto.existencia.articulo.sboSicop.sicopCodiClas + "</td>"
            + "<td>" + objeto.solArtiCant + "</td>");
    listado.append(tr);
}


function getReporteDepartamento(){
    
    var arti = document.getElementById("selectSicopPicker").value;
    var inicio = document.getElementById("fechaInicio").value;
    var fin = document.getElementById("fechaFinal").value;
    var dpto = document.getElementById("selectDptoPicker").value;


    
    if (inicio < fin) {
        $.ajax({type: "GET",
            url: "api/consumo2/" + arti + "/" + inicio + "/" + fin + "/" + dpto,
            success: lista
        });
    } else {
        alert("Fecha de incio debe ser menor a la fecha final")
    }
    
}


