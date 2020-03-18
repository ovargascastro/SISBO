/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function modalAgregaBodega() {

    $('#modalAgregarBodega').modal('show');

}

function agregarBodega() {

    if (confirm("Desea agregar el registro actual?")) {
        SboTbBodega = {
            bodeUbic: $("#bodeUbiA").val(),
            bodeDesc: $("#bodeDescA").val()
        };
        $.ajax({type: "POST",
            url: "api/Bodegas;charset=UTF-8",
            data: JSON.stringify(SboTbBodega),
            contentType: "application/json;charset=UTF-8",
            success: afterCreateBodega,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }
}

function afterCreateBodega() {

    $('#modalAgregarBodega').modal('hide');
    $('#agregaBodega').trigger("reset");
    buscarBodegas();

}


function listaBodega(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}

function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.bodeUbic + "</td>"
            + "<td>" + objeto.bodeDesc + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoBode(\"" + objeto.bodeIdPk + "\");'></td>"
            + "<td><img src='assets/img/trash-delete.png' onclick='eliminarBodega(\"" + objeto.bodeIdPk + "\");'></td>");
    listado.append(tr);

}

var bodegaActual;
function eliminarBodega(bodega) {
    $('#modalEliminar').modal('show');
    bodegaActual = bodega;
}

function deleteBodega() {

    alert(bodegaActual);
}

function buscarBodegas() {
    $.ajax({type: "GET",
        url: "api/BodegaListaOC?filtro=" + $("#filtro").val(),
        success: listaBodega
    });
}

$(document).ready(function () {
    buscarBodegas();
});


function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filtro");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
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


function deleteBodega() {


    if (confirm("Desea eliminar el registro actual?")) {
        SboTbBodega = {
            bodeIdPk: bodegaActual
        };
        $.ajax({type: "PUT",
            url: "api/ListaBodega;charset=UTF-8",
            data: JSON.stringify(SboTbBodega),
            contentType: "application/json;charset=UTF-8",
            success: ocultarDelete,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }

}

function ocultarDelete() {

    $('#modalEliminar').modal('hide');
    buscarBodegas();

}

var bodegaEdicion;
function infoBode(id) {

    bodegaEdicion = id;
    $.ajax({type: "GET",
        url: "api/Bodegas/" + id,
        success: mostrarBodega,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}




function mostrarBodega(bodega) {
    $("#bodeUbi").val(bodega.bodeUbic);
    $("#bodeDesc").val(bodega.bodeDesc);
    $('#modalEditarBode').modal('show');
}

function actualizarBodega() {


    if (confirm("Desea guardar el registro actual?")) {
        SboTbBodega = {
            bodeIdPk: bodegaEdicion,
            bodeUbic: $("#bodeUbi").val(),
            bodeDesc: $("#bodeDesc").val()
        };
        $.ajax({type: "PUT",
            url: "api/Bodegas;charset=UTF-8",
            data: JSON.stringify(SboTbBodega),
            contentType: "application/json;charset=UTF-8",
            success: ocultarActualizarBodega,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }

}

function ocultarActualizarBodega() {

    $('#modalEditarBode').modal('hide');
    buscarBodegas();

}






