/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function selectDeptos() {
    $.ajax({type: "GET",
        url: "api/departamentos",
        success: cargarSelectDptos,
        error: function (data) {
            alert('error');
        }
    });

}


function selectRoles(){
    
        $.ajax({type: "GET",
        url: "api/roles",
        success: caragrRoles,
        error: function (data) {
            alert('error');
        }
    });
    
}


function cargarSelectDptos(data) {

    cargarDeptos(data);
    cargarDeptosEdicion(data);
}

function caragrRoles(data){
    
    cargarRoles(data);
}

function cargarDeptosEdicion(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#selectDptoPickerEd").append('<option value="' + obj.deptoIdPk + '">' + '➤ ' + obj.deptoNomb + '</option>');

    });
    $('#selectDptoPickerEd').selectpicker('refresh');

}


function cargarRoles(data) {

    var jsonData = JSON.stringify(data);
    $.each(JSON.parse(jsonData), function (idx, obj) {
        $("#selectRoles").append('<option value="' + obj.rol_x_Perm_id_PK + '">' + '➤ ' + obj.rol_id_FK.permDesc + " - " +obj.perm_id_FK.rolDesc  + '</option>');

    });

}

function pickerEdicion() {
    $('#selectSicopPicker').addClass('selectpicker');
    $('#selectSicopPicker').attr('data-live-search', 'true');
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
    logged();
    buscarPersonas();
    selectDeptos();
    selectRoles();
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
            + "<td><img src='assets/img/edit.png' onclick='editarUsuario(\"" + objeto.persCedu + "\");'></td>");
    listado.append(tr);

}


var usuarioActual;


function editarUsuario(id) {

    usuarioActual = id;
    $.ajax({type: "GET",
        url: "api/gestionUsuarios/" + usuarioActual,
        success: mostrarUsuario,
        error: function (jqXHR) {
            alert("error");
        }
    });

}



function mostrarUsuario(objeto) {

    $("#cedUsuarioEd").val(objeto.persCedu);
    $("#ap1UsuarioEd").val(objeto.persApe1);
    $("#selectDptoPickerEd").val(objeto.departamento.deptoIdPk);
    $('#selectDptoPickerEd').selectpicker('refresh');
    $("#nombUsuarioEd").val(objeto.persNomb);
    $("#ap2UsuarioEd").val(objeto.persApe2);
    $("#textCont").val("Si deja el campo de contraseña en blanco se mantendrá la misma, de lo contrario se actualizará");
    if (objeto.pers_es_jefe === 1) {
        $('#customRadioInline12').prop('checked', true);
    } else {
        $('#customRadioInline22').prop('checked', true);
    }
    $('#editarUsuarioModal').modal('show');


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
        var rol = document.getElementById("selectRoles").value;
        var valorJefe = $('input[name=customRadioInline1]:checked', '#myForm').val();
        AbaaTbPersona = {
            persCedu: $("#cedUsuario").val(),
            persNomb: $("#nombUsuario").val(),
            rolAux: rol,
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
            error: ErrorUsuario
        });
    }
}


function ErrorUsuario(){
    
     alert("Error... Usuario ya existe!");
    
}

function afterCreateUsuario() {

    $('#myForm').trigger("reset");
    $('#agregarUsuarioModal').modal('hide');
    buscarPersonas();
}



function actualizarUsuario() {


    if (confirm("Desea guardar el registro actual?")) {
        var depto = document.getElementById("selectDptoPickerEd").value;
        var valorJefe = $('input[name=customRadioInline2]:checked', '#myForm2').val();
        AbaaTbPersona = {
            persCedu: usuarioActual,
            persNomb: $("#nombUsuarioEd").val(),
            persApe1: $("#ap1UsuarioEd").val(),
            persApe2: $("#ap2UsuarioEd").val(),
            passwAux: $("#passUsuarioEd").val(),
            jefe: valorJefe,
            departamento: {
                deptoIdPk: depto
            }
        };
        $.ajax({type: "PUT",
            url: "api/gestionUsuarios;charset=UTF-8",
            data: JSON.stringify(AbaaTbPersona),
            contentType: "application/json;charset=UTF-8",
            success: afterUpdateUsuario,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }


}

function afterUpdateUsuario() {
    $('#editarUsuarioModal').modal('hide');
    buscarPersonas();

}

