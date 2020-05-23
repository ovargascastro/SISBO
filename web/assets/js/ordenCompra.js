
// se agrega el articulo a la tabla provisional de la orden de compra
//con todos sus atributos, importante aun no se ha agregado a la base de datos
function agregarArticuloTemporal() {
    var depto = document.getElementById("selectDeptos").value;
    var art = document.getElementById("selectCatalogoArticulos").value;
    var unidad = document.getElementById("selectUnidadMedida").value;
    var proyect = 0;
    
    var e = document.getElementById("selectProyectos");
    var descProy = e.options[e.selectedIndex].text;
    if(descProy==='Seleccione una opcion'){
        descProy='Proyecto no asignado';
    }
    
    if (document.getElementById("proyectoCheck").checked === true) {
        proyect = document.getElementById("selectProyectos").value;
    }
    SboTbArticulo = {
        sboTbCatArticulo: {
            catIdPk: art
        },
        abaaProyectos: {
            proyIdPk: proyect,
            proyDesc: descProy
        },
        artMarc: $("#Marca").val(),
        artNumeSeri: $("#Serie").val(),
        artPrecio: $("#Precio").val(),
        abaaTbDepartamento: {
            deptoIdPk: depto
        },
        artDesc: $("#Descripcion").val(),
        artMode: $("#Modelo").val(),
        artCant: $("#Cantidad").val(),
        artUnidadMedida: unidad,
        artCodiPresup: $("#codPresupuestario").val()
    };
    $.ajax({type: "POST",
        url: "api/articulostemporales",
        data: JSON.stringify(SboTbArticulo),
        contentType: "application/json",
        success: exito,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });


}
//al ejecutar correctamente la funcion se limpian los campos de texto de los articulos
function exito() {
    limpiar();
    $('#modalArticulo').modal('hide');
    buscar2();
}
//se resetea los articulos de la orden
function limpiar() {
    $('#articulosOrden').trigger("reset");
}
// se listan los datos de los articulos temporales
function buscar2() {
    $.ajax({type: "GET",
        url: "api/articulostemporales?temporales=" + $("#Modelo").val(),
        success: listaArticulosTemporales
    });
}
//se elimina el articulo agregado a la orden de compra temporal
function eliminarArt(id){
   if(confirm("Desea eliminar el articulo?") ){
           $.ajax({type: "DELETE", 
          url:"api/articulostemporales/"+id, 
          success: buscar2,
          error: function(status){ alert(errorMessage(status));}                 
        }); 
    }
  }

//se listan los articulos existentes en el catalogo de articulos
function selectCatArticulos() {
    $.ajax({type: "GET",
        url: "api/catArticulos?filtro=" + " ",
        success: function (data) {
            $.each(data, function (key, catArt) {
                $("#selectCatalogoArticulos").append('<option value=' + catArt.catIdPk + '>' + catArt.catDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });

}
//se resetea el articulo elegido en el select
function resetearSelectCatArticulos(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
}
//se agrega los datos a la tabla de la orden de compra en la base de datos
function agregarOrdenCompra() {
    var provee = document.getElementById("selectProveedores").value;
    var fecha = document.getElementById("fechaOrden").value;
    var fecha2 = fecha.toDate("yyyy-mm-dd");

    SboTbOrdenCompra = {
        abaaTbProveedor: {
            proveIdProvePk: provee
        },

        ocFecha: fecha2,
        ocEsta: "asignar codigos",
        ocPlazoEntrega: $("#plazoEntrega").val(),
        ocEntregarA: $("#entregarA").val()
    };
    $.ajax({type: "POST",
        url: "api/ordenes",
        data: JSON.stringify(SboTbOrdenCompra),
        contentType: "application/json",
//        success: alert(p),
        success: agregarArticulos,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}

//se agrega los articulos en la base
function agregarArticulos() {
    $.ajax({type: "POST",
        url: "api/articulos",
        contentType: "application/json",
        success: salirOrden,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}

// se redirecciona a la pagina principal
function salirOrden() {
    location.href = "index.jsp"; 
}
// formato de la fecha
String.prototype.toDate = function (format)
{
    var normalized = this.replace(/[^a-zA-Z0-9]/g, '-');
    var normalizedFormat = format.toLowerCase().replace(/[^a-zA-Z0-9]/g, '-');
    var formatItems = normalizedFormat.split('-');
    var dateItems = normalized.split('-');

    var monthIndex = formatItems.indexOf("mm");
    var dayIndex = formatItems.indexOf("dd");
    var yearIndex = formatItems.indexOf("yyyy");

    var today = new Date();

    var year = yearIndex > -1 ? dateItems[yearIndex] : today.getFullYear();
    var month = monthIndex > -1 ? dateItems[monthIndex] - 1 : today.getMonth() - 1;
    var day = dayIndex > -1 ? dateItems[dayIndex] : today.getDate();

    return new Date(year, month, day);
};
//se agrega el proyecto a la base en caso de que la orden pertenezca a uno
function proyecto() {
    var proy;
    if (document.getElementById("proyectoCheck").checked === true) {
        proy = document.getElementById("selectProyectos").value;
    } else {
        proy = 0;
    }
    alert(proy);

}

//se listan las ordenes de compra
function buscarOrdenes() {

    $.ajax({type: "GET",
        url: "api/ordenes?filtro=" + $("#filtro").val(),
        success: listaOrdenes
    });
    
}
// se listan las ordenes que se le debe asignar un codigo contable
function estadoConta() {

    $.ajax({type: "GET",
        url: "api/ordCont?filtro=" + $("#filtro").val(),
        success: listaOrdenes
    });
}

//formato de la fecha
function formatDate(fecha) {
    var dia = fecha.substring(8, 10);
    var mes = fecha.substring(5, 7);
    var annio = fecha.substring(0, 4);
    var newFecha = dia + "/" + mes + "/" + annio;
    return newFecha;
}

//se listan los articulos que pertenecen a una orden de compra
function articulosXorden(filtro){
    
        $.ajax({type: "GET",
        url: "api/artxordenc?filtro=" + filtro,
        success: listaArticulos
    });

    $('#listaArticulos').modal('show');
}

//se listan losa articulos de una orden por asignarsele codigo contable
function articulosXordenConta(filtro){
    
        $.ajax({type: "GET",
        url: "api/artInner?filtro=" + filtro,
        success: listaArticulos
    });

    $('#listaArticulos').modal('show');
}
// se agrega un departamento en la tabla
function agregaDepartamento(objeto) {
    var listado = $("#departamentosRow");
    listado.html("");
    objeto.forEach((a) => {
        filaDepartamentos(listado, a);
    });

}
//se agrega una fila en la tabla para listar departamentos
function filaDepartamentos(listado, objeto) {

    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.abaaTbDepartamento.deptoNomb + "</td>");
    listado.append(tr);
}

//se agrega un proyecto nuevo a la tabla provisional de proyecto
function agregaProyecto(objeto) {
    var listado = $("#proyectosRow");
    listado.html("");
    objeto.forEach((a) => {
        filaProyectos(listado, a);
    });

}
//se agrega una fila a la tabla de proyectos
function filaProyectos(listado, objeto) {

    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.abaaProyectos.proyDesc + "</td>");
    listado.append(tr);
}
//verificar datos de sesion
$(document).ready(function () {
    logged();
});