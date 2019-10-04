
function selectProyectos() {
    $.ajax({type: "GET",
        url: "api/proyectos",
        success: function (data) {
            $.each(data, function (key, proyec) {
                $("#selectProyectos").append('<option value=' + proyec.proyIdPk + '>' + proyec.proyDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}

function resetearSelectProyectos(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

