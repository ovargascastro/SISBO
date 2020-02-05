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

function modalEditarSicop(obj){
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