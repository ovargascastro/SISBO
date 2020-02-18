/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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


function selectDeptos() {
    $.ajax({type: "GET",
        url: "api/departamentos",
        success: function (data) {
            $.each(data, function (key, depto) {
                $("#SelectDptos").append('<option value=' + depto.deptoIdPk + '>' + depto.deptoNomb + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}



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


$(document).ready(function () {
    selectSicop();
    selectDeptos();
    selectBodegas();
});


function getExistencias() {
    var depto = document.getElementById("SelectDptos").value;
    var arti = document.getElementById("selectSicop").value;
    var bodeg = document.getElementById("SelectBodegas").value;


    $.ajax({type: "GET",
        url: "api/Existencias/"+bodeg+"/"+depto+"/"+arti,
        success: listaExist
    });
}



function listaExist(personas) {
    var listado = $("#listadoExistencias");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}

function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sboTbBodega.bodeDesc + "</td>"
            + "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>"
            + "<td>" + objeto.sboTbSicop.sicopDesc + "</td>"
            + "<td>" + objeto.exisCant + "</td>");
    listado.append(tr);

}