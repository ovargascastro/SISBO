
function selectDeptos() {
//resetearSelectDeptos(document.getElementById("selectDeptos"));
//$("#selectDeptos").append('<option value="0" selected disabled = "true"> Seleccione una opcion </option>');
    $.ajax({type: "GET",
        url: "api/departamentos",
        success: function (data) {
            $.each(data, function (key, depto) {
                $("#selectDeptos").append('<option value=' + depto.deptoIdPk + '>' + depto.deptoNomb + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}

function resetearSelectDeptos(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}


