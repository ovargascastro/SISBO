//se listan los datos de los proveedores en el select
function selectProveedores() {
    $.ajax({type: "GET",
        url: "api/proveedores/orden",
        success: function (data) {
            $.each(data, function (key, prove) {
                $("#selectProveedores").append('<option value=' + prove.proveIdProvePk + '>' + prove.proveNomb + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}
//se listan los proveedores 
function selecProve() {
    var id = document.getElementById("selectProveedores").value;
    $.ajax({type: "GET",
        url: "api/proveedores/" + id,
        success: mostrarProveedor,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}
//se muestran los datos de los proveedores
function mostrarProveedor(prove) {
    $("#codigoProveedor").val(prove.proveCodigo);
    $("#email").val(prove.proveCorreo);
    $("#Cedula").val(prove.proveCedula);
    $("#Telefono").val(prove.proveTelefono);
    $("#Fax").val(prove.proveFax);
}
//se limpia la opcion seleccionada en el select
function resetearSelectProveedores(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
}

//se buscan proveedor por id
function buscarProvs() {
    $.ajax({type: "GET",
//        url:"api/proveedores/api/subfamilias/" + filtro,
        url: "api/proveedores/filtro?fil=" + $("#filtro").val(),
        success: listaProvs
    });
}
//se muesta informacion de proveedor
function infoProveedor(id) {

    variableProveedorActual = id;
    $.ajax({type: "GET",
        url: "api/proveedores/" + id,
        success: mostrarProv,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}


//se muestran los datos de los proveedores
function mostrarProv(prov) {

    //cedAuxiliar = per.cedula;
    $('#actualizaProv').trigger("reset");
    $("#nombProv").val(prov.proveNomb);
    $("#codProv").val(prov.proveCodigo);
    $("#telProv").val(prov.proveTelefono);
    $("#faxProv").val(prov.proveFax);
    $("#cedProv").val(prov.proveCedula);
    $("#correoProv").val(prov.proveCorreo);

    mostrarModalInfoProv();

}
//se hace visible el modal de proveedores
function mostrarModalInfoProv() {
    $('#modalProveedor').modal('show');
    // document.getElementById('infopersonal').style.display = 'block';
}
//se mustra modal con informacion de proveedor
function mostrarModalInfoProvAgregar() {
    $('#agregarProv').trigger("reset");
    $('#modalProveedorAgreg').modal('show');
}



//se modifican los datos de un proveedor con los ingresados por el usuario
function actualizarProveedor() {


    if (confirm("Desea guardar el registro actual?")) {
        AbaaTbProveedor = {
            proveIdProvePk: variableProveedorActual,
            proveCodigo: $("#codProv").val(),
            proveCedula: $("#cedProv").val(),
            proveTelefono: $("#telProv").val(),
            proveCorreo: $("#correoProv").val(),
            proveFax: $("#faxProv").val(),
            proveNomb: $("#nombProv").val()
        };
        $.ajax({type: "PUT",
            url: "api/proveedores;charset=UTF-8",
            data: JSON.stringify(AbaaTbProveedor),
            contentType: "application/json;charset=UTF-8",
            success: ocultarModalInfoProv,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }

}


//se agrega un proveedor nuevo con todos sus datos
function agregarProveedor() {

    if (confirm("Desea agregar el registro actual?")) {
        AbaaTbProveedor = {
            proveCodigo: $("#codProv2").val(),
            proveCedula: $("#cedProv2").val(),
            proveTelefono: $("#telProv2").val(),
            proveCorreo: $("#correoProv2").val(),
            proveFax: $("#faxProv2").val(),
            proveNomb: $("#nombProv2").val()
        };
        $.ajax({type: "POST",
            url: "api/proveedores;charset=UTF-8",
            data: JSON.stringify(AbaaTbProveedor),
            contentType: "application/json;charset=UTF-8",
            success: afterCreateProv,
            error: function (jqXHR) {
                alert("Error");
            }
        });
    }
}



//depues de crear un proveedor se limpian los campos de txto y se esconde el modal
function afterCreateProv() {

    $('#agregarProv').trigger("reset");
    $('#modalProveedorAgreg').modal('hide');
    buscarProvs();
    

}


    
//datos de inicio de sesion
$(document).ready(function () {
    logged();
});
