function selectProveedores() {
//    resetearSelectProveedores(document.getElementById("selectProveedores"));
//    $("#selectProveedores").append('<option value="0" selected disabled = "true"> Seleccione una opcion </option>');
    $.ajax({type: "GET",
        url: "api/proveedores",
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

function selecProve(){
    var id = document.getElementById("selectProveedores").value;
    $.ajax({type: "GET",
            url: "api/proveedores/" + id,
            success: mostrarProveedor,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
}

function mostrarProveedor(prove){
    $("#codigoProveedor").val(prove.proveCodigo);
    $("#email").val(prove.proveCorreo);
    $("#Cedula").val(prove.proveCedula);
    $("#Telefono").val(prove.proveTelefono);
    $("#Fax").val(prove.proveFax);
}

function resetearSelectProveedores(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}


