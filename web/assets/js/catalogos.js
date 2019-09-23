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


function mostrarFamilia(fam) {
    $("#codigoFamilia").val(fam.famiIdPk);
    $("#descripFamilia").val(fam.famiDesc);
    $('#modalEditarFam').modal('show');
}
var catArtActual;
function mostrarCatArt(art) {
    catArtActual = art.catIdPk;
    $("#descripArt").val(art.catDesc);
    $("#selectSubFam").val(art.sboTbSubFamilia.subFamiIdPk);
   // selectSubFamilias();
    $('#modalArticulo').modal('show');
}

function mostrarSubFamilia(subfam) {
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

function actualizarFamilia() {

    SboTbFamilia = {
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
var e = document.getElementById("selectFamilias");
var strUser = e.options[e.selectedIndex].value;
    
    SboTbSubFamilia = {
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> bbf1f7e578a21250e7389f0239ac4d9082f53a72

}
function abrirModalAgregaSubFam() {
    $('#modalAgregarSubFamilia').modal('show');

}
function abrirModalAgregaFam() {
    $('#modalAgregarFamilia').modal('show');

}


function resetearSelectFam(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

function resetearSelectSubFam(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}


<<<<<<< HEAD
=======
=======

>>>>>>> bbf1f7e578a21250e7389f0239ac4d9082f53a72
     $("#selectSubFam").val("");
   // selectAgregaSubFamilias();


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
<<<<<<< HEAD
>>>>>>> e75747bfae3579dbade9e58753922e42e7891b89
=======
>>>>>>> bbf1f7e578a21250e7389f0239ac4d9082f53a72
