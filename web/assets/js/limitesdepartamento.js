function modalAgregaBodega() {

    $('#modalAgregarBodega').modal('show');

}

function agregarLimite() {

    if (confirm("Desea agregar el registro actual?")) {
        
        SboTbLimiteDpto = {
            abaaTbDepartamento: {deptoIdPk: $("#SelectDptosAdd").val()},
            sboSicop: { sicopId: $("#SelectSicopAdd").val()},
            limite:$("#LimiteAdd").val()
            
        };
        $.ajax({type: "POST",
            url: "api/limiDepa",
            data: JSON.stringify(SboTbLimiteDpto),
            contentType: "application/json",
            success: afterCreateLimite,
            error: function (jqXHR) {
                alert("El límite ya existe");
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
    selectDeptos();
    selectDeptosAdd();
    selectSicopAdd();
    logged();
});


function getLimites() {
    var depto = document.getElementById("SelectDptos").value;
    var arti = document.getElementById("selectSicop").value;
   
    $.ajax({type: "GET",
        url: "api/limiDepa/"  + depto + "/" + arti,
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
            + "<td>" + objeto.sboSicop.sicopDesc + "</td>"
            + "<td>" + objeto.limite + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoLimi(\"" + objeto.abaaTbDepartamento.deptoIdPk + "\",\"" + objeto.sboSicop.sicopId + "\");'></td>"
            + "<td><img src='assets/img/trash-delete.png' onclick='eliminarLimite(\"" + objeto.abaaTbDepartamento.deptoIdPk + "\",\"" + objeto.sboSicop.sicopId + "\");'></td>");
    listado.append(tr);
}


var dpEdicion;
var artEdicion;
function infoLimi(id,id2) {

    dpEdicion = id;
    artEdicion = id2;
    $.ajax({type: "GET",
        url: "api/limiDepa/" + id+ "/" + id2,
        success: mostrarLimite,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}

function mostrarLimite(objeto) {
    $("#EditDepa").val(objeto[0].abaaTbDepartamento.deptoIdPk);
    $("#EditDepa2").val(objeto[0].abaaTbDepartamento.deptoNomb);
    $("#EditArti").val(objeto[0].sboSicop.sicopId);
    $("#EditArti2").val(objeto[0].sboSicop.sicopDesc);
    $("#EditLimi").val(objeto[0].limite);
    $('#modalEditarLimi').modal('show');
}

var idExistActual = 0;
function editarExist(id, cant) {
    idExistActual = id;
    $('#modalEditarLimi').modal('show');
    $("#existAct").val(cant);
}

function actualizarLimite() {
     SboTbLimiteDpto = {
            abaaTbDepartamento: {deptoIdPk: $("#EditDepa").val()},
            sboSicop: { sicopId: $("#EditArti").val()},
            limite:$("#EditLimi").val()
            
        };
        $.ajax({type: "PUT",
            url: "api/limiDepa;charset=UTF-8",
            data: JSON.stringify(SboTbLimiteDpto),
            contentType: "application/json;charset=UTF-8",
            success: ocultarEditarLimite,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }


function ocultarEditarLimite() {
    $('#modalEditarLimi').modal('hide');
   getLimites();
}


var dptActual;
var artActual;
function eliminarLimite(limite,limite2) {
    $('#modalEliminar').modal('show');
    dptActual = limite;
    $("#DeleteDepa").val(limite);
    artActual = limite2;
    $("#DeleteArti").val(limite2);
    
}

function deleteLimite() {

    
    if (confirm("Desea eliminar el registro actual?")) {
        SboTbLimiteDpto = {
            abaaTbDepartamento: {deptoIdPk: $("#DeleteDepa").val()},
            sboSicop: { sicopId: $("#DeleteArti").val()}
        };
        $.ajax({type: "DELETE",
            url: "api/limiDepa;charset=UTF-8",
            data: JSON.stringify(SboTbLimiteDpto),
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
    getLimites();

}




