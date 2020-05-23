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


function getReporteDepartamento() {

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


function reportePDF() {
    var arti = document.getElementById("selectSicopPicker").value;
    var inicio = document.getElementById("fechaInicio").value;
    var fin = document.getElementById("fechaFinal").value;

    if (inicio < fin) {
        $.ajax({type: "POST",
            url: "api/Reporte/" + arti + "/" + inicio + "/" + fin,
            success: alert("Reporte Generado"),
            error: function (data) {
                alert('error');
            }
        });
    } else {
        alert("Fecha de incio debe ser menor a la fecha final");
    }
}

function prueba() {
    alert("prueba");
}

function imprimir() {
    var arti = document.getElementById("selectSicopPicker").value;
    var inicio = document.getElementById("fechaInicio").value;
    var fin = document.getElementById("fechaFinal").value;
    var dpto = document.getElementById("selectDptoPicker").value;

    if ((arti !== "0") && (inicio !== "") && (fin !== "") && (dpto !== "0")) {
        if (inicio < fin) {
            $.ajax({type: "GET",
                url: "api/consumo2/" + arti + "/" + inicio + "/" + fin + "/" + dpto,
                success: lista2
            });
            $('#ImprimirReporte').modal('show');
        } else {
            alert("Fecha de incio debe ser menor a la fecha final");
        }
    } else {
        alert("Debe seleccionar los parámetros para poder imprimir el reporte");
    }
}

function lista2(personas) {
    var listado = $("#listaArticulosReporte");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}
//se utiliza para llenar las filas de la funcion anterior
function fila2(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.existencia.articulo.sboSicop.sicopDesc + "</td>"
            + "<td>" + objeto.existencia.articulo.sboSicop.sicopCodiInden + "</td>"
            + "<td>" + objeto.existencia.articulo.sboSicop.sicopCodiClas + "</td>"
            + "<td>" + objeto.solArtiCant + "</td>");
    listado.append(tr);
}

//imprimir JS trabajar desde aqui

document.getElementById('export').addEventListener('click',
        PDF);

var specialElementHandlers = {
    // element with id of "bypass" - jQuery style selector
    '.no-export': function (element, renderer) {
        // true = "handled elsewhere, bypass text extraction"
        return true;
    }
};

var getImageFromUrl = function (url, callback) {
    var img = new Image();
    img.onError = function () {
        alert('Cannot load image: "' + url + '"');
    };
    img.onload = function () {
        callback(img);
    };
    img.src = url;
}

function exportPDF(imgData) {
    var doc = new jsPDF('p', 'pt', 'a4');
    doc.addImage(imgData, 'JPEG', 0, 0, 100, 90);
    var source = document.getElementById('content').innerHTML;
    var margins = {
        top: 80,
        bottom: 10,
        left: 10,
        width: 595
    };

    doc.fromHTML(
            source, // HTML string or DOM elem ref.
            margins.left,
            margins.top, {
                'width': margins.width,
                'elementHandlers': specialElementHandlers
            },
            function (dispose) {
                doc.save('Reporte de Consumo por Departamento.pdf');
            }, margins);
}

function PDF() {
    getImageFromUrl('assets/img/Escudo2.jpg', exportPDF);
}
