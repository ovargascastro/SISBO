function modalAgregaBodega() {

    $('#modalAgregarBodega').modal('show');

}
function agregarLimite() {

    if (confirm("Desea agregar el registro actual?")) {
        SboTbLimiteDpto = {
            sboSicop: $("#SelectSicopAdd").val(),
            abaaTbDepartamento: $("#SelectSicopAdd").val(),
            limite:$("#LimiteAdd").val()
            
        };
        $.ajax({type: "POST",
            url: "api/limiDepa",
            data: JSON.stringify(SboTbLimiteDpto),
            contentType: "application/json;charset=UTF-8",
            success: afterCreateLimite,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }
}

function afterCreateLimite() {

    $('#modalAgregarBodega').modal('hide');
    $('#agregaBodega').trigger("reset");
    buscarBodegas();

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

function selectDeptosAdd() {
    $.ajax({type: "GET",
        url: "api/departamentos",
        success: function (data) {
            $.each(data, function (key, depto) {
                $("#SelectDptosAdd").append('<option value=' + depto.deptoIdPk + '>' + depto.deptoNomb + '</option>');
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

function selectSicopAdd() {
    $.ajax({type: "GET",
        url: "api/Sicop",
        success: function (data) {
            $.each(data, function (key, obj) {
                $("#SelectSicopAdd").append('<option value=' + obj.sicopId + '>' + obj.sicopDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}

$(document).ready(function () {
    selectSicop();
    selectSicopAdd();
    selectDeptos();
    selectDeptosAdd();
    logged();
});


function getLimites() {
    var depto = document.getElementById("SelectDptos").value;
    var arti = document.getElementById("selectSicop").value;
   
    $.ajax({type: "GET",
        url: "api/LimitesDepartamento/"  + depto + "/" + arti,
        success: listaExist
    });
}

function listaExist(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}

function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>"
            + "<td>" + objeto.sboTbSicop.sicopDesc + "</td>"
            + "<td>" + objeto.SboTbLimiteDpto.limite + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoBode(\"" + objeto.SboTbLimiteDpto.id + "\");'></td>"
            + "<td><img src='assets/img/trash-delete.png' onclick='eliminarBodega(\"" + objeto.SboTbLimiteDpto.id + "\");'></td>");
    listado.append(tr);
}

var idExistActual = 0;
function editarExist(id, cant) {
    idExistActual = id;
    $('#modalEditarExist').modal('show');
    $("#existAct").val(cant);
}

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

function ocultarEditarExist(list) {
    $('#modalEditarExist').modal('hide');
    listaExist(list);
}


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

