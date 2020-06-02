/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// se muestra el modal para agregar una bodega
function modalAgregaBodega() {

    $('#modalAgregarBodega').modal('show');

}

//funcion para ejecutar los metodos necesarios para agregar una bodega en la base de datos
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

// se ejecuta cuando la funcion agregarBodega() es exitosa y se esconde el modal
// para agregar una bodega y se limpian los campos de texto
function afterCreateBodega() {

    $('#modalAgregarBodega').modal('hide');
    $('#agregaBodega').trigger("reset");
    buscarBodegas();

}

// funcion necesaria para mostrar las bodegas en la tabla
function listaBodega(personas) {
    var listado = $("#listado");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}
//se ejectura en caso de que la funcion listaBodega se invoque
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.bodeUbic + "</td>"
            + "<td>" + objeto.bodeDesc + "</td>"
            + "<td><img src='assets/img/edit.png' onclick='infoBode(\"" + objeto.bodeIdPk + "\");'></td>"
            + "<td><img src='assets/img/trash-delete.png' onclick='eliminarBodega(\"" + objeto.bodeIdPk + "\",\"" + objeto.bodeDesc + "\");'></td>");
    listado.append(tr);

}


// se elimina la bodega seleccionada 
// en este caso solo se realiza un bloqueo de la bodega y 
//no se mostrara en la lista de bodegas
var bodegaActual;
var bodegaDescrip;
function eliminarBodega(bodega, descrip) {
    $('#modalEliminar').modal('show');
    
    bodegaActual = bodega;
    bodegaDescrip = descrip;
    
    
}


//alerta al "eliminar" una bodega
function deleteBodega() {

    alert(bodegaActual);
}

//funcion donde se buscaran las bodegas existentes en la base de datos por medio del id 
function buscarBodegas() {
    $.ajax({type: "GET",
        url: "api/BodegaListaOC?filtro=" + $("#filtro").val(),
        success: listaBodega
    });
}
// se ejecuta la funcion anterior
$(document).ready(function () {
    logged();
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

// se hara el bloqueo del registro de la bodega 
// y no se mostrara mas en la lista 
function deleteBodega() {

    
    if (confirm("Desea eliminar el registro actual?")) {
        SboTbBodega = {
            bodeIdPk: bodegaActual,
            bodeDesc: bodegaDescrip
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
// se oculta el modal de eliminar
function ocultarDelete() {

    $('#modalEliminar').modal('hide');
    buscarBodegas();

}

// se obtiene la informacion de la bodega seleccionada
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



//se obtiene los datos correspendientes a la bodega seleccionada
function mostrarBodega(bodega) {
    $("#bodeUbi").val(bodega.bodeUbic);
    $("#bodeDesc").val(bodega.bodeDesc);
    $('#modalEditarBode').modal('show');
}

// se realiza las modificaciones especificadas por el usuario
//se actualiza la bodega seleccionada en la base de datos
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

// se oculta el modal de editar bodega
function ocultarActualizarBodega() {

    $('#modalEditarBode').modal('hide');
    buscarBodegas();

}






