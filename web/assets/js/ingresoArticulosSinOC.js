
//funcion necesaria para agregar el registro a la base de datos con la informacion proporcionada por el usuario 
function agregarArticulos() {
    var depto = document.getElementById("selectDeptos").value;
    var art = document.getElementById("selectCatalogoArticulos").value;
    var unidad = document.getElementById("selectUnidadMedida").value;
    var sicop = document.getElementById("selectSicop").value;
    var tipo = document.getElementById("selectTipoIng").value;
    var cantRest = 0;
    
    var fechaIngreso = document.getElementById("AddArtFIngreso").value;
    var fechaIngreso2 = fechaIngreso.toDate("yyyy-mm-dd");
    
    var fechaVencimiento = document.getElementById("AddArtFVencimiento").value;
    var fechaVencimiento2;
    if(fechaVencimiento.length>0){
        fechaVencimiento2 = fechaVencimiento.toDate("yyyy-mm-dd");
    }else{
        fechaVencimiento2 = null;
    }
    

    SboTbArticulo = {
        sboTbCatArticulo: {
            catIdPk: art
        },
        artMarc: $("#AddArtMarca").val(),
        artNumeSeri: $("#AddArtNSerie").val(),
        artPrecio: $("#Precio").val(),
        abaaTbDepartamento: {
            deptoIdPk: depto
        },
        sboSicop: {
            sicopId: sicop
        },
        artiTipoIngr: tipo,
        artDesc: $("#AddArtDescripcion").val(),
        artMode: $("#AddArtModelo").val(),
        artCant: $("#AddArtCant").val(),
        artFingr: fechaIngreso2,
        artFvenc: fechaVencimiento2,
        artCantRest: cantRest,
        artUnidadMedida: unidad
    };
    $.ajax({type: "POST",
        url: "api/articulos/articulo;charset=UTF-8",
        data: JSON.stringify(SboTbArticulo),
       contentType: "application/json;charset=UTF-8",
        success: agregarExistencias,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}

//darle formato a las fechas
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

//se agrega la existencia a la base de datos con los datos correspondientes
function agregarExistencias() {
    //SboTbExistenciaId id, SboTbBodega sboTbBodega, int SboTbEsta, SboTbArticulo articulo

    var bodeg = document.getElementById("AddArtBodega").value;
    existencia = {
        sboTbBodega: [{bodeIdPk: $("#AddArtBodega").val()}],
        SboTbBodega:{
            bodeIdPk: bodeg
        }
    };
    $.ajax({type: "PUT",
        url: "api/Existencias;charset=utf-8",
        data: JSON.stringify(existencia),
        contentType: "application/json;charset=utf-8",
        success: limpiar,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}
//se redirige a la pagina principal
function salir() {
    location.href = "index.jsp"; 
}
//se limpian los campos del ingreso de articulos
function limpiar(){
    alert("Articulos Ingresados!");
    location.href = "presentation/bodega/ingresoArticulos.jsp"; 
}
