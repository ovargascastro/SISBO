
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
//funcion para cargar los datos apenas se ingrese al url de existencias
$(document).ready(function () {
    selectSicop();
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
}
//se utliza para mostrar los datos en las filas de la funcion anterior
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sboTbBodega.bodeDesc + "</td>"
            + "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>"
            + "<td>" + objeto.sboTbSicop.sicopDesc + "</td>"
            + "<td>" + objeto.exisCant + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='editarExist(\"" + objeto.idE + "\",\"" + objeto.exisCant + "\");'></td>");
    listado.append(tr);
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
    $('#modalEditarExist').modal('hide');
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
