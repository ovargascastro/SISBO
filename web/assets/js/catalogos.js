/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function buscar() {

    var e = document.getElementById("selectcatalogos");
    var strUser = e.options[e.selectedIndex].value;

    switch (strUser) {
        case "1":
            buscarFamilias();
            $("#filtro").val('');
            break;
        case "2":
            buscarSubFamilias();
            $("#filtro").val('');
            break;
        case "3":
            buscarCatArticulos();
            $("#filtro").val('');
            break;

    }
}

function buscarFamilias() {
    $.ajax({type: "GET",
        url: "api/familias?filtro=" + $("#filtro").val(),
        success: listaFam
    });
}

function buscarSubFamilias() {
    $.ajax({type: "GET",
        url: "api/subfamilias?filtro=" + $("#filtro").val(),
        success: listaSubFam
    });
}

function buscarCatArticulos() {
    $.ajax({type: "GET",
        url: "api/catArticulos?filtro=" + $("#filtro").val(),
        success: listaCatArt
    });

}

function abrirModalEditar(filtro) {
    var e = document.getElementById("selectcatalogos");
    var strUser = e.options[e.selectedIndex].value;
    if (strUser === "1") {
        $.ajax({type: "GET",
            url: "api/familias/" + filtro,
            success: mostrarFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    } else if (strUser === "2") {
        $.ajax({type: "GET",
            url: "api/subfamilias/" + filtro,
            success: mostrarSubFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });

    } else if (strUser === "3") {


        $.ajax({type: "GET",
            url: "api/catArticulos/" + filtro,
            success: mostrarCatArt,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}

var famEsta;
function mostrarFamilia(fam) {
      
    famEsta = fam.famiEstado;
    $("#codigoFamilia").val(fam.famiIdPk);
    $("#descripFamilia").val(fam.famiDesc); 
    $('#modalEditarFam').modal('show');
}
var catArtEstado;
var catArtActual;
function mostrarCatArt(art) {
   
    catArtEstado=art.artCat_Estado;
     console.log(catArtEstado);
    catArtActual = art.catIdPk;
    $("#descripArt").val(art.catDesc);
    $("#selectSubFam").val(art.sboTbSubFamilia.subFamiIdPk);
   // selectSubFamilias();
    $('#modalArticulo').modal('show');
}
var subfamEsta;
function mostrarSubFamilia(subfam) {
    
    subfamEsta=subfam.subFamiEstado;
    console.log(subfamEsta);
    $("#codigoSubFam").val(subfam.subFamiIdPk);
    $("#descripSubFam").val(subfam.subFamiDesc);
    $("#selectFamilias").val(subfam.sboTbFamilia.famiIdPk);
    
   // selectFamilias();
    $('#modalSubFam').modal('show');
}


function abrirModalEliminar() {
    $('#modalEliminar').modal('show');

}
function concatenarBusqueda() {
    var valor = $("#selectcatalogos option:selected").text();
    $("#catalogode").text('Catalogo de ' + valor);
    buscar();
}


function cargarSelects(){
    selectFamilias();
    selectSubFamilias();
    selectAgregaFamilias();
    selectAgregaSubFamilias();
}

function selectFamilias() {

    $.ajax({type: "GET",
        url: "api/familias?filtro=" + $("#filtro").val(),
        success: function (data) {
            $.each(data, function (key, fam) {
                $("#selectFamilias").append('<option value=' + fam.famiIdPk + '>' + fam.famiDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });

}


function selectSubFamilias() {

    $.ajax({type: "GET",
        url: "api/subfamilias?filtro=" + $("#filtro").val(),
        success: function (data) {
            $.each(data, function (key, subf) {
                $("#selectSubFam").append('<option value=' + subf.subFamiIdPk + '>' + subf.subFamiDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });

}

function selectCatArticulos() {
//resetearSelectCatArticulos(document.getElementById("selectArticulos"));
//$("#selectArticulos").append('<option value="0" selected disabled = "true"> Seleccione una opcion </option>');
    $.ajax({type: "GET",
        url: "api/catArticulos?nombre=" + $("#codigo").val(),
        success: function (data) {
            $.each(data, function (key, catArt) {
                $("#selectArticulos").append('<option value=' + catArt.catIdPk + '>' + catArt.catDesc + '</option>');
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
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

function actualizarFamilia() {
 console.log(famEsta);
    SboTbFamilia = {
        famiEstado: famEsta,
        famiIdPk: $("#codigoFamilia").val(),
        famiDesc: $("#descripFamilia").val()
       
    };
    $.ajax({type: "PUT",
        url: "api/familias",
        data: JSON.stringify(SboTbFamilia),
        contentType: "application/json",
        success: afterUpdateFm,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateFm(){
    buscarFamilias();
    $('#modalEditarFam').modal('hide');

}

function actualizarSubFamilia() {
    console.log(subfamEsta);
var e = document.getElementById("selectFamilias");
var strUser = e.options[e.selectedIndex].value;
    
    SboTbSubFamilia = {
        subFamiEstado: subfamEsta,
        subFamiIdPk: $("#codigoSubFam").val(),
        subFamiDesc: $("#descripSubFam").val(),
       
        sboTbFamilia:{
            famiIdPk: strUser
        }
    };
    $.ajax({type: "PUT",
        url: "api/subfamilias",
        data: JSON.stringify(SboTbSubFamilia),
        contentType: "application/json",
        success: afterUpdateSubFm,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateSubFm(){
    buscarSubFamilias();
    $('#modalSubFam').modal('hide');

}


function actualizarCatArticulo() {
var e = document.getElementById("selectSubFam");
var strUser = e.options[e.selectedIndex].value;


    
    SboTbCatArticulo = {
        catDesc: $("#descripArt").val(),
        catIdPk: catArtActual,
        artCat_Estado:catArtEstado,
       // subFamiDesc: $("#descripSubFam").val(),
        sboTbSubFamilia:{
            subFamiIdPk: strUser
        }
    };
    $.ajax({type: "PUT",
        url: "api/catArticulos",
        data: JSON.stringify(SboTbCatArticulo),
        contentType: "application/json",
        success: afterUpdateCatArt,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateCatArt(){
    buscarCatArticulos();
    $('#modalArticulo').modal('hide');

}

function agregarACatalogo(){
    
        var e = document.getElementById("selectcatalogos");
    var strUser = e.options[e.selectedIndex].value;

    switch (strUser) {
        case "1":
            abrirModalAgregaFam();
            $("#filtro").val('');
            break;
        case "2":
            abrirModalAgregaSubFam();
            $("#filtro").val('');
            break;
        case "3":
            abrirModalAgregaCatArticulo();
            $("#filtro").val('');
            break;

    }
    
}

function abrirModalAgregaCatArticulo(){
    $('#modalAgregarCatArticulo').modal('show');
     $("#selectSubFam").val("");
   // selectAgregaSubFamilias();
}

function selectAgregaSubFamilias() {

    $.ajax({type: "GET",
        url: "api/subfamilias?filtro=" + $("#filtro").val(),
        success: function (data) {
            $.each(data, function (key, subf) {
                $("#AgregarSubfamiliaCatArt").append('<option value=' + subf.subFamiIdPk + '>' + subf.subFamiDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });

}

function abrirModalAgregaSubFam(){
    $('#modalAgregarSubFamilia').modal('show');
        $("#AgregarFamiliaSubF").val("");
    //selectAgregaFamilias();
    
}

function selectAgregaFamilias() {

    $.ajax({type: "GET",
        url: "api/familias?filtro="+ $("#filtro").val(),
        success: function (data) {
            $.each(data, function (key, fam) {
                $("#AgregarFamiliaSubF").append('<option value=' + fam.famiIdPk + '>' + fam.famiDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });

}

function abrirModalAgregaFam(){
    $('#modalAgregarFamilia').modal('show');
    }
   function crearFamilia(){
//           var e = document.getElementById("Select2");
//           var strUser = e.options[e.selectedIndex].value;
           SboTbFamilia = {
        famiIdPk: $("#AgregarCodigoFam").val(),
        famiDesc: $("#AgregarDescripcionFam").val()
    };
            
            $.ajax({type: "POST", 
                  url:"api/familias", 
                  data: JSON.stringify(SboTbFamilia),
                  contentType: "application/json",
                  success: afterCreateFm,
                  error: function(jqXHR){ alert(errorMessage(jqXHR.status));}                 
                });      
      
}
function afterCreateFm(){
    buscarFamilias();
    $('#modalAgregarFamilia').modal('hide');
}

function crearSubFamilia() {
var e = document.getElementById("AgregarFamiliaSubF");
var strUser = e.options[e.selectedIndex].value;
    
    SboTbSubFamilia = {
        subFamiIdPk: $("#AgregarCodigoSubF").val(),
        subFamiDesc: $("#AgregarDescripcionSubF").val(),
        sboTbFamilia:{
            famiIdPk: strUser
        }
    };
    $.ajax({type: "POST",
        url: "api/subfamilias",
        data: JSON.stringify(SboTbSubFamilia),
        contentType: "application/json",
        success: afterCreateSubFm,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterCreateSubFm(){
    buscarSubFamilias();
    $('#modalAgregarSubFamilia').modal('hide');
}

function CrearCatArticulo() {
var e = document.getElementById("AgregarSubfamiliaCatArt");
var strUser = e.options[e.selectedIndex].value;


    
    SboTbCatArticulo = {
        catDesc: $("#AgregarDescipcionCatArt").val(),
        catCodSicop: $("#AgregarCodSicopCatArt").val(),
       // subFamiDesc: $("#descripSubFam").val(),
        sboTbSubFamilia:{
            subFamiIdPk: strUser
        }
    };
    $.ajax({type: "POST",
        url: "api/catArticulos",
        data: JSON.stringify(SboTbCatArticulo),
        contentType: "application/json",
        success: afterCreateCatArt,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterCreateCatArt(){
    buscarCatArticulos();
    $('#modalAgregarCatArticulo').modal('hide');

}

function abrirModalDesactivar(filtro) {
    var e = document.getElementById("selectcatalogos");
    var strUser = e.options[e.selectedIndex].value;
    
    if (strUser === "1") {
        $.ajax({type: "GET",
            url: "api/familias/" + filtro,
            success: desactivarFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    } else if (strUser === "2") {
        $.ajax({type: "GET",
            url: "api/subfamilias/" + filtro,
            success: desactivarSubFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });

    } else if (strUser === "3") {


        $.ajax({type: "GET",
            url: "api/catArticulos/" + filtro,
            success: desactivarCatArt,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}
var famidAct;
var famiDesAct;

function desactivarFamilia(fam){
famidAct  = fam.famiIdPk;
famiDesAct = fam.famiDesc;
//famiEstAct = fam.famiEstado;
    $('#modalDesactivar').modal('show');  
}

var subFamidAct;
var subFamiFami;
var subFamiDesAct;

function desactivarSubFamilia(Subfam){
    subFamidAct= Subfam.subFamiIdPk;
    subFamiFami = Subfam.sboTbFamilia;
    subFamiDesAct= Subfam.subFamiDesc;
   // subFamiEstAct=Subfam.subFamiEstado;
    $('#modalDesactivar').modal('show'); 
}
var catArtId;
var catArtDesc;
var catArtSubF;
var catArtEst;
function desactivarCatArt(articulo){
       catArtId = articulo.catIdPk;
       catArtDesc= articulo.catDesc;
       catArtSubF= articulo.sboTbSubFamilia;
       catArtEst= articulo.artCat_Estado;
    $('#modalDesactivar').modal('show'); 
}

var famiEstAct=0;
function actualizarEstadoFamilia() {

    SboTbFamilia = {
        famiIdPk: famidAct,
        famiDesc:famiDesAct,
        famiEstado:famiEstAct
        
    };
    $.ajax({type: "PUT",
        url: "api/familias",
        data: JSON.stringify(SboTbFamilia),
        contentType: "application/json",
        success: afterUpdateFmEs,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateFmEs(){
    buscarFamilias();
    $('#modalDesactivar').modal('hide');

}

var subFamiEstAct=0;
function actualizarEstadoSubFamilia() {
//var e = document.getElementById("selectFamilias");
//var strUser = e.options[e.selectedIndex].value;
    
    SboTbSubFamilia = {
        subFamiIdPk:subFamidAct,
        subFamiDesc: subFamiDesAct,
        sboTbFamilia:subFamiFami,
        subFamiEstado:subFamiEstAct
    };
    $.ajax({type: "PUT",
        url: "api/subfamilias",
        data: JSON.stringify(SboTbSubFamilia),
        contentType: "application/json",
        success: afterUpdateSubFmEs,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateSubFmEs(){
    buscarSubFamilias();
    $('#modalDesactivar').modal('hide');

}
var EstadoActual=0;
function actualizarEstadoCatArticulo() {

    SboTbCatArticulo = {
        catDesc:catArtDesc,
        catIdPk: catArtId,
        artCat_Estado:EstadoActual,
        sboTbSubFamilia:catArtSubF
    };
    $.ajax({type: "PUT",
        url: "api/catArticulos",
        data: JSON.stringify(SboTbCatArticulo),
        contentType: "application/json",
        success: afterUpdateCatArtEs,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

function afterUpdateCatArtEs(){
    buscarCatArticulos();
    $('#modalDesactivar').modal('hide');

}

function Desactivar() {
    var e = document.getElementById("selectcatalogos");
    var strUser = e.options[e.selectedIndex].value;
    
    if (strUser === "1") {
        $.ajax({type: "GET",
            url: "api/familias/",
            success: actualizarEstadoFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    } else if (strUser === "2") {
        $.ajax({type: "GET",
            url: "api/subfamilias/",
            success: actualizarEstadoSubFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });

    } else if (strUser === "3") {


        $.ajax({type: "GET",
            url: "api/catArticulos/",
            success: actualizarEstadoCatArticulo,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}