/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function selecArt() {

    var filtro = document.getElementById("selectDeptos").value;
    $.ajax({type: "GET",
        url: "api/Existencias?filtro=" + filtro,
        success: function (data) {
            $.each(data, function (key, art) {
                $("#selectArt").append('<option value=' + art.artIdPk + '>' + art.artDesc + '</option>');
            });
        },
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}

function resetearSelectArt(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
    selecArt();
}

function agregarArtTemp() {

    var art = document.getElementById("selectArt").value;
    SboTbArticulo = {
        artIdPk: art,
        cantSolArt: $("#cantidad").val()
    };
    $.ajax({type: "POST",
        url: "api/artSolTemp",
        data: JSON.stringify(SboTbArticulo),
        contentType: "application/json",
        success: buscar,
        error: function (jqXHR) {
            alert("Cantidad ingresada es mayor a la existente en bodega. Ingrese una cantida válida.");
        }
    });
}

function buscar() {
    $.ajax({type: "GET",
        url: "api/artSolTemp?temporales=" + $("#Modelo").val(),
        success: listaArtTemp
    });
    $('#formSolicitudArt').trigger("reset");
}



function agregarSolicitudArticulo() {
    var depto = document.getElementById("selectDeptos").value;
    var f = new Date();
    var fecha =f.getFullYear() + "-" + (f.getMonth() + 1) + "-" + f.getDate();
    var fecha2 = fecha.toDate("yyyy-mm-dd");
    SboTbSoliArti = {
        abaaTbDepartamento: {
            deptoIdPk: depto
        },
        abaaTbFuncionario: {
            funcIdPk: 1
        },
        solArtiEsta: "pendiente",
        solArtiFechSoli: fecha2
    };
    $.ajax({type: "POST",
        url: "api/solicitudArticulo",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: agregarSoliXart,
        error: function (jqXHR) {
            alert("Seleccione los artículos que requiere antes de enviar la solicitud");
        }
    });
}



function agregarSoliXart() {
    $.ajax({type: "POST",
        url: "api/artPorSol",
        contentType: "application/json",
        success: salirSolicitud,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}

function salirSolicitud() {
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

function formatDate(fecha) {
    var dia = fecha.substring(8, 10);
    var mes = fecha.substring(5, 7);
    var annio = fecha.substring(0, 4);
    var newFecha = dia + "/" + mes + "/" + annio;
    return newFecha;
}

function buscarListaSolicitudes() {

    $.ajax({type: "GET",
        url: "api/solicitudArticulo?filtro=" + $("#filtro").val(),
        success: listSoliArt,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
}

function articulosXSolicitud(filtro) {
    
    $.ajax({type: "GET",
        url: "api/artPorSol?filtro=" + filtro,
        success: listaArticulosxSol,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }

    });
    $('#listaArtxSol').modal('show');
}

function getExistencias() {

    var id = document.getElementById("selectArt").value;
    $.ajax({type: "GET",
        url: "api/cantExist?id="+id,
        success: muestraCantidad,
        error: function (jqXHR) {
            alert("no se pudo");
        }
    });
}

function muestraCantidad(cant){
    //alert("prueba");
    $("#cantidadExist").val(cant);
}

function buscarSolicitudxAprobar() {

    $.ajax({type: "GET",
        url: "api/soliAprobacion?filtro=" + $("#filtro").val(),
        success: listSoliArt,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
    
    
}

function buscarSolicitudVbJf() {

    $.ajax({type: "GET",
        url: "api/soliAprobacionJF?filtro=" + $("#filtro").val(),
        success: listSoliArtJF,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
    
    
}


function buscarSolicitudVbTI() {

    $.ajax({type: "GET",
        url: "api/soliAprobacionTI?filtro=" + $("#filtro").val(),
        success: listSoliArtTI,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
    
    
}

function abrirModalRechazar(){
        $("#motivo").val("");
        $('#Rechazar').modal('show');
   

}

function Aprobar(filtro){
    console.log(filtro);
 $.ajax({type: "GET",
            url:"api/soliAprobacionJF/" + filtro,
            success: mostraraprobarJF,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    
}

var solIdActual1;
var solFecha1;
var solDeparta1;
function mostraraprobarJF(soli) {
 solIdActual1 = soli.solArtiIdPk;
 solFecha1= soli.solArtiFechSoli;
 solDeparta1= soli.abaaTbDepartamento;
    console.log("estoy en el aprobarJEFE");
   // solEstado= soli.c;
        $('#Aprobar').modal('show');
        console.log(soli.solArtiIdPk);

}

function AprobarTI(filtro){
    console.log(filtro);
 $.ajax({type: "GET",
            url:"api/soliAprobacionTI/" + filtro,
            success: mostraraprobarTI,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    
}

var solIdActual2;
var solFecha2;
var solDeparta2;

function mostraraprobarTI(soli) {
 solIdActual2 = soli.solArtiIdPk;
 solFecha2= soli.solArtiFechSoli;
 solDeparta2= soli.abaaTbDepartamento;
 
    console.log("estoy en el aprobarIT");
  
   // solEstado= soli.c;
        $('#AprobarTI').modal('show');
        console.log(soli.solArtiIdPk);

}


function abrirModalAprobar(filtro){
    console.log(filtro);
 $.ajax({type: "GET",
            url:"api/soliAprobacion/" + filtro,
            success: mostrarXaprobar,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    
}
var VBJF;
var VBTI;
var solIdActual;
var solFecha;
var solDeparta;
function mostrarXaprobar(soli) {
 solIdActual = soli.solArtiIdPk;
 solFecha= soli.solArtiFechSoli;
 solDeparta= soli.abaaTbDepartamento;
 VBJF=soli.solArtiVistJefe;
 VBTI=soli.solArtiVistTi;
   // solEstado= soli.c;
     console.log(VBJF);
     console.log(VBTI);
     if(VBJF===true && VBTI===false){
         $('#modalAprobarJEFE').modal('show');     
     }
     else if(VBJF===false && VBTI===true){
         $('#modalAprobarTI').modal('show');     
     }
     else if(VBJF===true && VBTI===true){
         $('#modalAprobarAmbas').modal('show');
      }
     else
        $('#modalAprobar').modal('show');
        console.log(soli.solArtiIdPk);

}

   var solEstado="Aprobada";     
 function actualizarEstadoAprobacion() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual,
        solArtiFechSoli: solFecha,
        abaaTbDepartamento: solDeparta,
        solArtiEsta: solEstado
    };
    $.ajax({type: "PUT",
        url: "api/solicitudArticulo",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateApE,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateApE() {
    buscarSolicitudxAprobar();
    
   if(VBJF===true && VBTI===false){
         $('#modalAprobarJEFE').modal('hide');     
     }
     else if(VBJF===false && VBTI===true){
         $('#modalAprobarTI').modal('hide');     
     }
     else if(VBJF===true && VBTI===true){
         $('#modalAprobarAmbas').modal('hide');
      }
     else
        $('#modalAprobar').modal('hide');

}   

var solEstado2="PendienteVBJefe";     
 function actualizarEstadoJefe() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual,
        solArtiFechSoli: solFecha,
        abaaTbDepartamento: solDeparta,
        solArtiEsta: solEstado2
    };
    $.ajax({type: "PUT",
        url: "api/solicitudArticulo",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateApEsJf,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
function afterUpdateApEsJf() {
    buscarSolicitudxAprobar();
   if(VBJF===true && VBTI===false){
         $('#modalAprobarJEFE').modal('hide');     
     }
     else if(VBJF===false && VBTI===true){
         $('#modalAprobarTI').modal('hide');     
     }
     else if(VBJF===true && VBTI===true){
         $('#modalAprobarAmbas').modal('hide');
      }
     else
        $('#modalAprobar').modal('hide');

}   

var solEstado3="PendienteVBTI";     
 function actualizarEstadoTI() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual,
        solArtiFechSoli: solFecha,
        abaaTbDepartamento: solDeparta,
        solArtiEsta: solEstado3
    };
    $.ajax({type: "PUT",
        url: "api/solicitudArticulo",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateApEsTI,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
function afterUpdateApEsTI() {
    buscarSolicitudxAprobar();
    if(VBJF===true && VBTI===false){
         $('#modalAprobarJEFE').modal('hide');     
     }
     else if(VBJF===false && VBTI===true){
         $('#modalAprobarTI').modal('hide');     
     }
     else if(VBJF===true && VBTI===true){
         $('#modalAprobarAmbas').modal('hide');
      }
     else
        $('#modalAprobar').modal('hide');

}   


var solEstado4="Rechazado: ";     
 function actualizarEstadoRechazo() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual,
        solArtiFechSoli: solFecha,
        abaaTbDepartamento: solDeparta,
        solArtiEsta:solEstado4+$("#motivo").val()
    };
    $.ajax({type: "PUT",
        url: "api/solicitudArticulo",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateApEs,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}


function afterUpdateApEs() {
    buscarSolicitudxAprobar();
    $('#Rechazar').modal('hide');

}   

function imprimir(filtro) {
    
    
     $.ajax({type: "GET",
            url:"api/solicitudArticulo/" + filtro,
            success: mostrarsoli,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
        
        $.ajax({type: "GET",
        url: "api/artPorSol?filtro=" + filtro,
        success: listaArticulosxSolImp,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }

    });
    
}   

var a;
function mostrarsoli(soli) {
    
  console.log(soli);
    $('#SolicitudImprimir').modal('show');
   
   $("#num").val(soli.solArtiIdPk);  
   $("#departa").val(formatDate(soli.solArtiFechSoli));  
//    console.log(soli.abaaTbDepartamento.deptoNomb);
       // console.log(a);
}



var vistoBueno=1;
var solEstado1="VBJefeAprobado";     
 function actualizarEstadoVbJf() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual1,
        solArtiFechSoli: solFecha1,
        abaaTbDepartamento: solDeparta1,
        solArtiEsta: solEstado1,
        solArtiVistJefe:vistoBueno
    };
    $.ajax({type: "PUT",
        url: "api/soliAprobacionJF",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateVBJefe,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateVBJefe() {
    buscarSolicitudVbJf();
    $('#Aprobar').modal('hide');

}   


var solEstado5="Rechazado Jefe: ";     
 function actualizarEstadoRechazoJefe() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual1,
        solArtiFechSoli: solFecha1,
        abaaTbDepartamento: solDeparta1,
        solArtiEsta:solEstado5+$("#motivoJF").val(),
        solArtiVistJefe:0
    };
    $.ajax({type: "PUT",
        url: "api/soliAprobacionJF",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateRechJefe,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}


function afterUpdateRechJefe() {
    buscarSolicitudVbJf();
    $('#Rechazar').modal('hide');

}   


var vistoBueno2=1;
var solEstado6="VBTIAprobado";     
 function actualizarEstadoVbTI() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual2,
        solArtiFechSoli: solFecha2,
        abaaTbDepartamento: solDeparta2,
        solArtiEsta: solEstado6,
        solArtiVistTi:vistoBueno2
        
    };
    $.ajax({type: "PUT",
        url: "api/soliAprobacionTI",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateVBTI,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateVBTI() {
    buscarSolicitudVbTI();
    $('#AprobarTI').modal('hide');

}   

var solEstado7="Rechazado TI: ";     
 function actualizarEstadoRechazoTI() {
 console.log(solIdActual);
    SboTbSoliArti = {
        solArtiIdPk: solIdActual2,
        solArtiFechSoli: solFecha2,
        abaaTbDepartamento: solDeparta2,
        solArtiEsta:solEstado7+$("#motivoTI").val(),
        solArtiVistTi:0
    };
    $.ajax({type: "PUT",
        url: "api/soliAprobacionTI",
        data: JSON.stringify(SboTbSoliArti),
        contentType: "application/json",
        success: afterUpdateRechTI,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}


function afterUpdateRechTI() {
    buscarSolicitudVbTI();
    $('#Rechazar').modal('hide');

}   


function eliminaArt(id){
   if(confirm("Desea eliminar el articulo?") ){
           $.ajax({type: "DELETE", 
          url:"api/artSolTemp/"+id, 
          success: buscar,
          error: function(status){ alert(errorMessage(status));}                 
        }); 
    }
  }