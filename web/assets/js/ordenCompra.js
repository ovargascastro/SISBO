function agregarArticuloTemporal() {
    var depto = document.getElementById("selectDeptos").value;
    var art = document.getElementById("selectCatalogoArticulos").value;
    var unidad = document.getElementById("selectUnidadMedida").value;
    var proyect = 0;
    if (document.getElementById("proyectoCheck").checked === true) {
        proyect = document.getElementById("selectProyectos").value;
    }
    SboTbArticulo = {
        abaaProyectos: {
            proyIdPk: proyect
        },
        sboTbCatArticulo: {
            catIdPk: art
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

function exito() {
    limpiar();
    $('#modalArticulo').modal('hide');
    buscar2();
}

function limpiar() {
    $("#Descripcion").val('');
    $("#Modelo").val('');
    $("#Cantidad").val(''),
            $('#selectUnidadMedida option').prop('selected', function () {
        return this.defaultSelected;
    });
    $("#codPresupuestario").val('');
    $("#Marca").val('');
    $("#Serie").val('');
    $("#Precio").val('');
    $('#selectDeptos option').prop('selected', function () {
        return this.defaultSelected;
    });
    $('#selectCatalogoArticulos option').prop('selected', function () {
        return this.defaultSelected;
    });
}

function buscar2() {
    $.ajax({type: "GET",
        url: "api/articulostemporales?temporales=" + $("#Modelo").val(),
        success: listaArticulosTemporales
    });
}

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

function resetearSelectCatArticulos(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
}

function agregarOrdenCompra() {
    var provee = document.getElementById("selectProveedores").value;
    var fecha = document.getElementById("fechaOrden").value;
    var fecha2 = fecha.toDate("yyyy-mm-dd");
    var plazo = document.getElementById("plazoEntrega").value;
    var plazo2 = plazo.toDate("yyyy-mm-dd");
//    var p = 0;
//    if(document.getElementById("proyectoCheck").checked === true){
//            p = document.getElementById("selectProyectos").value;
//        }
    SboTbOrdenCompra = {
        abaaTbProveedor: {
            proveIdProvePk: provee
        },
//        abaaProyectos: {
//            proyIdPk: p
//        },
        ocFecha: fecha2,
        ocEsta: "no procesada",
        ocPlazoEntrega: plazo2,
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


function salirOrden() {
    var ruta = "index.jsp";
    window.location = ruta;
}

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

function proyecto() {
    var proy;
    if (document.getElementById("proyectoCheck").checked === true) {
        proy = document.getElementById("selectProyectos").value;
    } else {
        proy = 0;
    }
    alert(proy);

}

function buscarOrdenes() {

    $.ajax({type: "GET",
        url: "api/ordenes?filtro=" + $("#filtro").val(),
        success: listaOrdenes
    });
}

function estadoConta() {

    $.ajax({type: "GET",
        url: "api/ordCont?filtro=" + $("#filtro").val(),
        success: listaOrdenes
    });
}

function formatDate(fecha) {
    var dia = fecha.substring(8, 10);
    var mes = fecha.substring(5, 7);
    var annio = fecha.substring(0, 4);
    var newFecha = dia + "/" + mes + "/" + annio;
    return newFecha;
}

function articulosXorden(filtro){
    
        $.ajax({type: "GET",
        url: "api/artxordenc?filtro=" + filtro,
        success: listaArticulos
    });

    $('#listaArticulos').modal('show');
}


function articulosXordenConta(filtro){
    
        $.ajax({type: "GET",
        url: "api/artInner?filtro=" + filtro,
        success: listaArticulos
    });

    $('#listaArticulos').modal('show');
}
