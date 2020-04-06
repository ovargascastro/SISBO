//se muestran por medio de un select los proyectos en la base de datos
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
//se limpia la opcion seleccionada en los proyectos
function resetearSelectProyectos(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

