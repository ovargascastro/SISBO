/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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


function abrirModalAgregar() {
    $('#agregarUsuarioModal').modal('show');
}


$(document).ready(function () {
    buscarPersonas();
    selectDeptos();


});




function buscarPersonas() {
    $.ajax({type: "GET",
        url: "api/gestionUsuarios",
        success: listaPersonas
    });
}


// funcion necesaria para mostrar las personas/usuarios en la tabla
function listaPersonas(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}
//se ejectura en caso de que la funcion listaPersonas se invoque
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.persCedu + "</td>"
            + "<td>" + objeto.persNomb + "</td>"
            + "<td>" + objeto.persApe1 + "</td>"
            + "<td>" + objeto.persApe2 + "</td>"
            + "<td>" + objeto.departamento.deptoNomb + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoPer(\"" + objeto.PersCedu + "\");'></td>");
    listado.append(tr);

}

function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("filtro");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
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


function agregarUsuario() {

    if (confirm("Desea agregar el registro actual?")) {

        var depto = document.getElementById("selectDptoPicker").value;
        var valorJefe = $('input[name=customRadioInline1]:checked', '#myForm').val();
        AbaaTbPersona = {
            persCedu: $("#cedUsuario").val(),
            persNomb: $("#nombUsuario").val(),
            persApe1: $("#ap1Usuario").val(),
            persApe2: $("#ap2Usuario").val(),
            passwAux: $("#passUsuario").val(),
            jefe: valorJefe,
            departamento: {
                deptoIdPk: depto
            }
        };
        $.ajax({type: "POST",
            url: "api/gestionUsuarios;charset=UTF-8",
            data: JSON.stringify(AbaaTbPersona),
            contentType: "application/json;charset=UTF-8",
            success: afterCreateUsuario,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }
}


function afterCreateUsuario() {

    $('#myForm').trigger("reset");
    $('#agregarUsuarioModal').modal('hide');
    buscarPersonas();
}

