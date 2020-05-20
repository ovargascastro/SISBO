
//se listan las bodegas en el select
function selectBodegas() {
    var vacio = "";
    $.ajax({type: "GET",
        url: "api/BodegaListaOC?filtro=" + vacio,
        success: function (data) {
            $.each(data, function (key, bod) {
                $("#SelectBodegas").append('<option value=' + bod.bodeIdPk + '>' + bod.bodeIdPk + ' - ' + bod.bodeDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}
//se listan los departamentos en el select
function selectDeptos() {
    $.ajax({type: "GET",
        url: "api/departamentos",
        success: selectDeptoPicker,
        error: function (data) {
            alert('error');
        }
    });
}
//se listan los articulos de sicop en el select
function selectSicop() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: function (data) {
            $.each(data, function (key, obj) {
                $("#selectSicop").append('<option value=' + obj.sicopId + '>' + obj.sicopDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}

function selectSicop2() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: selectSicopPicker,
        error: function (data) {
            alert('error');
        }
    });

}

function selectSicopPicker(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#selectSicop").append('<option value="' + obj.sicopId + '">' + '➤ ' + obj.sicopDesc + '</option>');

    });
    $('#selectSicop').selectpicker('refresh');

}
function selectDeptoPicker(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#SelectDptos").append('<option value="' + obj.deptoIdPk + '">' + '➤ ' + obj.deptoNomb + '</option>');

    });
    $('#SelectDptos').selectpicker('refresh');
}

$(document).ready(function () {
    selectSicop2();
    selectDeptos();
    selectBodegas();
    logged();
});

//se obtiene las existencias dependiendo de la bodega, el departamento y el articulo seleccionado
function getExistencias() {
    var depto = document.getElementById("SelectDptos").value;
    var arti = document.getElementById("selectSicop").value;
    var bodeg = document.getElementById("SelectBodegas").value;

    $.ajax({type: "GET",
        url: "api/Existencias/" + bodeg + "/" + depto + "/" + arti,
        success: listaExist
    });

}

//se listan las existencias en la tabla
function listaExist(personas) {
    var listado = $("#listadoExistencias");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
    Nregistros();
}
//se utliza para mostrar los datos en las filas de la funcion anterior
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + formatDate(objeto.existFingr) + "</td>"
            + "<td>" + objeto.sboTbBodega.bodeDesc + "</td>"
            + "<td>" + objeto.articulo.abaaTbDepartamento.deptoNomb + "</td>"
            + "<td>" + objeto.articulo.sboSicop.sicopDesc + "</td>"
            + "<td>" + '₡ ' + objeto.articulo.artPrecio + "</td>"
            + "<td><img class='small-img' src='assets/img/info(1).png' onclick='abrirArticulo(\"" + objeto.articulo.artIdPk + "\");'></td>"
            + "<td><img class='small-img' src='assets/img/trash-delete.png' onclick='eliminarExistenciaV(\"" + objeto.id + "\");'></td>");
    listado.append(tr);

}

var existenciaActual = 0;

function eliminarExistenciaV(id) {
    existenciaActual = id;
    $('#modalEliminaExist').modal('show');

}



function eliminarExistencia() {
    SboTbExistencia = {
        id: existenciaActual
    };
    $.ajax({type: "POST",
        url: "api/Existencias;charset=UTF-8",
        data: JSON.stringify(SboTbExistencia),
        contentType: "application/json;charset=UTF-8",
        success: ocultarEditarExist,
        error: function (jqXHR) {
            alert("Error");
        }
    });

}

function formatDate(fecha) {
    var dia = fecha.substring(8, 10);
    var mes = fecha.substring(5, 7);
    var annio = fecha.substring(0, 4);
    var newFecha = dia + "/" + mes + "/" + annio;
    return newFecha;
}

//se abre el modal para editar la cantidad de la existencia seleccionada
var idExistActual = 0;
function editarExist(id, cant) {
    idExistActual = id;
    $('#modalEditarExist').modal('show');
    $("#existAct").val(cant);
}

//se edita la cantidad de la existencia seleccionada
function actualizarExistencia() {
    if (confirm("Desea guardar el registro actual?")) {
        SboTbExistencia = {
            idE: idExistActual,
            exisCant: $("#nuevExist").val()
        };
        $.ajax({type: "POST",
            url: "api/Existencias;charset=UTF-8",
            data: JSON.stringify(SboTbExistencia),
            contentType: "application/json;charset=UTF-8",
            success: ocultarEditarExist,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }
}
//se oculta el modal de editar cantidad de existencias
function ocultarEditarExist(list) {
    $('#modalEliminaExist').modal('hide');
    listaExist(list);
}

//funcion para darle formato al select de articulos de sicop
function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = $('#selectSicop option:selected').text();
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
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


function abrirArticulo(id) {

    solicitarDatosArticulo(id);
    $('#informacionArt').modal('show');

}


function cargarBotonInfo(catalogoID) {
    var linea = "<img src='assets/img/info(1).png' onclick='abrirModalInfoArticulo(\"" + catalogoID + "\")'>";
    $("#botonArticuloInfo").empty().append(linea);
}

function solicitarDatosArticulo(id) {
    $.ajax({type: "GET",
        url: "api/infoArticulo/" + id,
        success: mostrarDatosArt
    });
}


function mostrarDatosArt(objeto) {

    $("#ArticuloInfo").val(objeto.sboTbCatArticulo.catDesc);
    $("#DescripcionInfo").val(objeto.artDesc);
    $("#ModeloInfo").val(objeto.artMode);
    $("#MarcaInfo").val(objeto.artMarc);
    $("#OrdenInfo").val(objeto.sboTbOrdenCompra.ocIdPk);
    $("#SicopInfo").val(objeto.sboSicop.sicopDesc);
    $("#PrecioActualInfo").val(objeto.artPrecioActual);
    $("#ArtIdInfo").val(objeto.artIdPk);
}

function Nregistros() {
    var table = document.getElementById("myTable");
    var tbodyRowCount = table.tBodies[0].rows.length; // 3

    if (tbodyRowCount > 0) {
        document.getElementById("nReg").innerHTML = "Número de registros : " + tbodyRowCount;
    } else {
        document.getElementById("nReg").innerHTML = "Número de registros : 0";
    }
}

function actualizarArticulo() {
    articulo = {
        artIdPk: $("#ArtIdInfo").val(),
        artPrecioActual: $("#PrecioActualInfo").val()
    };
    $.ajax({type: "PUT",
        url: "api/articulos/precioActual",
        data: JSON.stringify(articulo),
        contentType: "application/json",
        success: $('#informacionArt').modal('hide'),
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}

function imprimirExistencias() {
    var depto = document.getElementById("SelectDptos").value;
    var arti = document.getElementById("selectSicop").value;
    var bodeg = document.getElementById("SelectBodegas").value;

    $.ajax({type: "GET",
        url: "api/Existencias/" + bodeg + "/" + depto + "/" + arti,
        success: listaExist2
    });
    $('#ImprimirReporteExistencias').modal('show');
    alert("NADAAAAAAA");
}
var nRegistros;
//se listan las existencias en la tabla
function listaExist2(personas) {
    var listado = $("#listaArticulosExistencia");
    listado.html("");
    personas.forEach((p) => {
        fila2(listado, p);
    });
    Nregistros2();
}
//se utliza para mostrar los datos en las filas de la funcion anterior
function fila2(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + formatDate(objeto.existFingr) + "</td>"
            + "<td>" + objeto.sboTbBodega.bodeDesc + "</td>"
            + "<td>" + objeto.articulo.abaaTbDepartamento.deptoNomb + "</td>"
            + "<td>" + objeto.articulo.sboSicop.sicopDesc + "</td>"
            + "<td>" + objeto.articulo.artPrecio + "</td>");
    listado.append(tr);
}

function Nregistros2() {
    var table = document.getElementById("myTable");
    var tbodyRowCount = table.tBodies[0].rows.length;
    if (tbodyRowCount > 0) {
        document.getElementById("nRegImp").innerHTML = "Número de registros : " + tbodyRowCount;
        nRegistros = tbodyRowCount;
    } else {
        document.getElementById("nRegImp").innerHTML = "Número de registros : 0";
        nRegistros = 0;
    }
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
                doc.save('Reporte de artículos existentes en bodega.pdf');
            }, margins);
}

function PDF() {
    getImageFromUrl('assets/img/Escudo2.jpg', exportPDF);
}


function ReportePDF() {
    var bode = document.getElementById("SelectBodegas").value;
    var depa = document.getElementById("SelectDptos").value;
    
    {
        $.ajax({type: "POST",
            url: "api/tomaFisica/" + bode + "/" + depa,
            success: alert("Reporte Generado"),
            error: function(data){
                alert("Error al generar el reporte");
            }
        });
    } 

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
        alert("Debe generar el reporte para poder imprimirlo");
    }
}

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
