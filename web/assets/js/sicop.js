/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//se listan los datos en un select correspondientes a Sicop
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



window.addEventListener('load', cargar, false);

// lista los articulos de sicop
function cargar() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: listaSicop
    });
}

// se muestra en una tabla los datos de la tabla sicop 
function listaSicop(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}
// se agrega una fila a la tabla donde se muestran los datos de sicop
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sicopCodiClas + "</td>"
            + "<td>" + objeto.sicopCodiInden + "</td>"
            + "<td>" + objeto.sicopDesc + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoSicop(\"" + objeto.sicopId + "\");'></td>");
    listado.append(tr);

}
//se llena el modal con los datos del articulo seleccionado
function modalEditarSicop(obj) {
    $("#codClas").val(obj.sicopCodiClas);
    $("#codId").val(obj.sicopCodiInden);
    $("#desc").val(obj.sicopDesc);
    $('#modalEditarSicop').modal('show');
}


// se muestra los datos que corresponden a un registro de sicop
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

// se modifica el articulo de sicop con los nuevos datos
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

// se agrega un nuevo registro a la tabla de sicop en la base
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

// despues de crear el articulo se resetean los campos de texto y se esconde el modal
function afterCreateSicop() {

    alert("Registro Agregado!");
    $('#agregaSicop').trigger("reset");
    $('#modalAgregarSicop').modal('hide');
    cargar();

}
// se oculta el modal de editar sicop y se limpian los campos de texto de dicho modal
function ocultarSicop() {
    alert("Registro Editado!");
    $('#actualizaSicop').trigger("reset");
    $('#modalEditarSicop').modal('hide');
    cargar();

}
// se muestra el modal de agregar articulo sicop
function modalAgregaSicop() {

    $('#modalAgregarSicop').modal('show');

}

//se busca un registro de sicop por el id digitado por el usuario
function buscarSicopFiltro() {
    $.ajax({type: "GET",
//        url:"api/proveedores/api/subfamilias/" + filtro,
        url: "api/Sicop/filtro?fil=" + $("#filtro").val(),
        success: listaSicop
    });
}

function exitoFiltro() {

}


// formato para mostrar los datos 
function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filtro");
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

//datos de inicio de sesion
$(document).ready(function () {
    logged();
});
