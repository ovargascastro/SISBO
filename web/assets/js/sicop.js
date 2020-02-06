/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



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


window.addEventListener('load', cargar, false);


function cargar() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: listaSicop
    });
}


function listaSicop(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}

function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sicopCodiClas + "</td>"
            + "<td>" + objeto.sicopCodiInden + "</td>"
            + "<td>" + objeto.sicopDesc + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoSicop(\"" + objeto.sicopId + "\");'></td>");
    listado.append(tr);

}

function modalEditarSicop(obj) {
    $("#codClas").val(obj.sicopCodiClas);
    $("#codId").val(obj.sicopCodiInden);
    $("#desc").val(obj.sicopDesc);
    $('#modalEditarSicop').modal('show');
}



function infoSicop(id) {

    variableSicopActual = id;
    $.ajax({type: "GET",
        url: "api/Sicop/" + id,
        success: modalEditarSicop,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}


function actualizarSicop() {


    if (confirm("Desea guardar el registro actual?")) {
        SboSicop = {
            sicopId: variableSicopActual,
            sicopCodiInden: $("#codId").val(),
            sicopCodiClas: $("#codClas").val(),
            sicopDesc: $("#desc").val()
        };
        $.ajax({type: "PUT",
            url: "api/Sicop;charset=UTF-8",
            data: JSON.stringify(SboSicop),
            contentType: "application/json;charset=UTF-8",
            success: ocultarSicop,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }

}


function agregarSicop() {

    if (confirm("Desea agregar el registro actual?")) {
        SboSicop = {
            sicopCodiInden: $("#codId2").val(),
            sicopCodiClas: $("#codClas2").val(),
            sicopDesc: $("#desc2").val()
        };
        $.ajax({type: "POST",
            url: "api/Sicop;charset=UTF-8",
            data: JSON.stringify(SboSicop),
            contentType: "application/json;charset=UTF-8",
            success: afterCreateSicop,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }
}


function afterCreateSicop() {

    $('#agregaSicop').trigger("reset");
    $('#modalAgregarSicop').modal('hide');
    cargar();

}

function ocultarSicop() {
    $('#actualizaSicop').trigger("reset");
    $('#modalEditarSicop').modal('hide');
    cargar();

}

function modalAgregaSicop() {

    $('#modalAgregarSicop').modal('show');

}

function buscarSicopFiltro() {
    $.ajax({type: "GET",
//        url:"api/proveedores/api/subfamilias/" + filtro,
        url: "api/Sicop/filtro?fil=" + $("#filtro").val(),
        success: listaSicop
    });
}

function exitoFiltro(){
    
}

