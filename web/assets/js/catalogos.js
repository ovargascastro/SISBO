/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// funcion para realizar las busquedas dependiendo si se desea
//buscar una familia, subfamilia, catalogo de articulo, catalogo contable
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
        case "4":
            buscarCatContables();
            $("#filtro").val('');
            break;

    }
}


//se buscara la familia por id
function buscarFamilias() {
    $.ajax({type: "GET",
        url: "api/familias?filtro=" + $("#filtro").val(),
        success: listaFam
    });
}

//se buscara la subfamilia por id
function buscarSubFamilias() {
    $.ajax({type: "GET",
        url: "api/subfamilias?filtro=" + $("#filtro").val(),
        success: listaSubFam
    });
}
//se buscara en el catalgo de articulos por id
function buscarCatArticulos() {
    $.ajax({type: "GET",
        url: "api/catArticulos?filtro=" + $("#filtro").val(),
        success: listaCatArt
    });

}
//se buscara en el catalogo contable por id
function buscarCatContables() {
    $.ajax({type: "GET",
        url: "api/contables?filtro=" + $("#filtro").val(),
        success: listaCatConta
    });

}
 
 
 //se abre el modal de editar dependiendo si es una familia,subfamilia, el catalogo de articulos o de codigos contables
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
    } else if (strUser === "4") {


        $.ajax({type: "GET",
            url: "api/contables/" + filtro,
            success: mostrarCatCont,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}
// se coloca los datos actuales de la familia seleccionada en los campos correspondientes
var famEsta;
function mostrarFamilia(fam) {

    famEsta = fam.famiEstado;
    $("#codigoFamilia").val(fam.famiIdPk);
    $("#descripFamilia").val(fam.famiDesc);
    $('#modalEditarFam').modal('show');
}

// se coloca los datos actuales del codigo contable seleccionada en los campos correspondientes
var contAct;
var contEsta;
function mostrarCatCont(cont) {
    contAct = cont.cntIdPk;
    contEsta = cont.cntEst;
    $("#codigoContable").val(cont.cntCodi);
    $("#descripContable").val(cont.cntDesc);
    $("#NivelContable").val(cont.cntNivel);
    $('#modalEditarCatContable').modal('show');
}
// se coloca los datos actuales del articulo seleccionada en los campos correspondientes
var catArtEstado;
var catArtActual;
function mostrarCatArt(art) {

    catArtEstado = art.artCat_Estado;
    console.log(catArtEstado);
    catArtActual = art.catIdPk;
    $("#descripArt").val(art.catDesc);
    $("#selectSubFam").val(art.sboTbSubFamilia.subFamiIdPk);
    // selectSubFamilias();
    $('#modalArticulo').modal('show');
}
// se coloca los datos actuales de la subfamilia seleccionada en los campos correspondientes

var subfamEsta;
function mostrarSubFamilia(subfam) {

    subfamEsta = subfam.subFamiEstado;
    console.log(subfamEsta);
    $("#codigoSubFam").val(subfam.subFamiIdPk);
    $("#descripSubFam").val(subfam.subFamiDesc);
    $("#selectFamilias").val(subfam.sboTbFamilia.famiIdPk);

    // selectFamilias();
    $('#modalSubFam').modal('show');
}

//se muestra el modal para "eliminar"
function abrirModalEliminar() {
    $('#modalEliminar').modal('show');

}
//se busca dependiendo del catalogo seleccionado
function concatenarBusqueda() {
    var valor = $("#selectcatalogos option:selected").text();
    $("#catalogode").text('Catalogo de ' + valor);
    buscar();
}

//funcion para mostrar los catalogos
function cargarSelects() {
    selectFamilias();
    selectSubFamilias();
    selectAgregaFamilias();
    selectAgregaSubFamilias();
}

//se buscan los registron del catalogo de familias
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

//se buscan los registron del catalogo de subfamilias

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

//se buscan los registron del catalogo de articulos

function selectCatArticulos() {
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
// se reinician los datos del catalogo de articulos
function resetearSelectCatArticulos(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
}

//se actualiza los registron del catalogo de familias

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

//se utiliza en caso de que la funcion actualizarFamilia haya sido exitosa
// esconde el modal  de editar familia
function afterUpdateFm() {
    alert("Registro Editado!");
    buscarFamilias();
    selectFamilias();
    $('#modalEditarFam').modal('hide');

}
//se actualiza los registron del catalogo de subfamilias
function actualizarSubFamilia() {
    console.log(subfamEsta);
    var e = document.getElementById("selectFamilias");
    var strUser = e.options[e.selectedIndex].value;

    SboTbSubFamilia = {
        subFamiEstado: subfamEsta,
        subFamiIdPk: $("#codigoSubFam").val(),
        subFamiDesc: $("#descripSubFam").val(),

        sboTbFamilia: {
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
//se utiliza en caso de que la funcion actualizarsubFamilia haya sido exitosa
// esconde el modal  de editar subfamilia
function afterUpdateSubFm() {
    alert("Registro Editado!");
    selectSubFamilias();
    buscarSubFamilias();
    $('#modalSubFam').modal('hide');
}


//se actualiza el catalogo de codigos contables
function actualizarCatContable() {
    console.log(contEsta);
    SboTbCatContable = {
        cntEst: contEsta,
        cntIdPk: contAct,
        cntCodi: $("#codigoContable").val(),
        cntNivel: $("#NivelContable").val(),
        cntDesc: $("#descripContable").val()
    };
    $.ajax({type: "PUT",
        url: "api/contables",
        data: JSON.stringify(SboTbCatContable),
        contentType: "application/json",
        success: afterUpdateCt,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}

//se utiliza en caso de que la funcion actualizarCatContable haya sido exitosa
// esconde modal de codigos contables
function afterUpdateCt() {
    alert("Registro Editado!");
    buscarCatContables();
    $('#modalEditarCatContable').modal('hide');

}






//se actualiza el catalogo de articulos
function actualizarCatArticulo() {
    var e = document.getElementById("selectSubFam");
    var strUser = e.options[e.selectedIndex].value;



    SboTbCatArticulo = {
        catDesc: $("#descripArt").val(),
        catIdPk: catArtActual,
        artCat_Estado: catArtEstado,
        // subFamiDesc: $("#descripSubFam").val(),
        sboTbSubFamilia: {
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

//se utiliza en caso de que la funcion actualizarCatArt haya sido exitosa
// esconde modal de catalogo de articulos
function afterUpdateCatArt() {
    alert("Registro Editado!");
    buscarCatArticulos();
    $('#modalArticulo').modal('hide');

}

//se agrega un registro dependiendo del catalogo seleccionado
//puede ser una familia, subfamilia, catalgo de codigos contables o al catalogo de articulos
//dependiendo del caso se abre el modal para realizar la agregacion
function agregarACatalogo() {

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
        case "4":
            abrirModalAgregaCodContable();
            $("#filtro").val('');
            break;

    }

}
//se hace visible el modal para agregar codigos contables
function abrirModalAgregaCodContable() {
    $('#modalAgregarCatContable').modal('show');
}
//se hace visible el modal para agregar articulos 

function abrirModalAgregaCatArticulo() {
    $('#modalAgregarCatArticulo').modal('show');
    $("#selectSubFam").val("");
    // selectAgregaSubFamilias();
}
//se hace visible el modal para agregar subfamilias

function abrirModalAgregaSubFam() {
    $('#modalAgregarSubFamilia').modal('show');
}
//se hace visible el modal para agregar familias

function abrirModalAgregaFam() {
    $('#modalAgregarFamilia').modal('show');
}
// se limpia el select de familias
function resetearSelectFam(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
}
// se limpia el select de subfamilias
function resetearSelectSubFam(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {
        selectbox.remove(i);
    }
}



// se muestran las subfamilias en el select 
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
//se muestra el modal para agregar una subfamilia 
function abrirModalAgregaSubFam() {
    $('#modalAgregarSubFamilia').modal('show');
    $("#AgregarFamiliaSubF").val("");
    //selectAgregaFamilias();

}

//se cargan en el select las familias a elegir
function selectAgregaFamilias() {

    $.ajax({type: "GET",
        url: "api/familias?filtro=" + $("#filtro").val(),
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
//se muestra el modal para agregar una familia 
function abrirModalAgregaFam() {
    $('#modalAgregarFamilia').modal('show');
}

//se agrega una familia a la base de datos
function crearFamilia() {
//           var e = document.getElementById("Select2");
//           var strUser = e.options[e.selectedIndex].value;
    SboTbFamilia = {
        famiIdPk: $("#AgregarCodigoFam").val(),
        famiDesc: $("#AgregarDescripcionFam").val()
    };

    $.ajax({type: "POST",
        url: "api/familias",
        data: JSON.stringify(SboTbFamilia),
        contentType: "application/json",
        success: afterCreateFm,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}

//se oculta el modal para agregar una familia despues de haber sido creada
function afterCreateFm() {
    alert("Registro Agregado!");
    selectFamilias();
    buscarFamilias();
    $('#modalAgregarFamilia').modal('hide');
}


//se agrega un registro al catalogo contable a la base de datos

function crearCatContable() {
//           var e = document.getElementById("Select2");
//           var strUser = e.options[e.selectedIndex].value;
    SboTbCatContable = {
        cntCodi: $("#AgregarCodCatCont").val(),
        cntDesc: $("#AgregarDescipcionCatCont").val(),
        cntNivel: $("#AgregarNivelCatCont").val()
    };

    $.ajax({type: "POST",
        url: "api/contables",
        data: JSON.stringify(SboTbCatContable),
        contentType: "application/json",
        success: afterCreateCont,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });

}

//se oculta el modal para agregar un codigo contable despues de haber sido creada
//se listan de nuevo todos los codigos
function afterCreateCont() {
    alert("Registro Agregado!");
    buscarCatContables();
    $('#modalAgregarCatContable').modal('hide');
}

// se crea una familia con sus atributos en la base de datos
function crearSubFamilia() {
    var e = document.getElementById("AgregarFamiliaSubF");
    var strUser = e.options[e.selectedIndex].value;

    SboTbSubFamilia = {
        subFamiIdPk: $("#AgregarCodigoSubF").val(),
        subFamiDesc: $("#AgregarDescripcionSubF").val(),
        sboTbFamilia: {
            famiIdPk: strUser
        }
    };
    $.ajax({
        type: "POST",
        url: "api/subfamilias",
        data: JSON.stringify(SboTbSubFamilia),
        contentType: "application/json",
        success: afterCreateSubFm,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
//se oculta el modal para agregar una subfamilia despues de haber sido creada
//se listan las subfamilias
function afterCreateSubFm() {
    alert("Registro Agregado!");
    buscarSubFamilias();
    selectAgregaFamilias();
    $('#modalAgregarSubFamilia').modal('hide');
}
//creamos un articulo en el catalogo
function CrearCatArticulo() {
    var e = document.getElementById("AgregarSubfamiliaCatArt");
    var strUser = e.options[e.selectedIndex].value;



    SboTbCatArticulo = {
        catDesc: $("#AgregarDescipcionCatArt").val(),
        catCodSicop: $("#AgregarCodSicopCatArt").val(),
        // subFamiDesc: $("#descripSubFam").val(),
        sboTbSubFamilia: {
            subFamiIdPk: strUser
        }
    };
    $.ajax({
        type: "POST",
        url: "api/catArticulos",
        data: JSON.stringify(SboTbCatArticulo),
        contentType: "application/json",
        success: afterCreateCatArt,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
//se oculta el modal para agregar una articulo despues de haber sido creada
//se listan los articulos existentes
function afterCreateCatArt() {
    alert("Registro Agregado!");
    buscarCatArticulos();
    selectAgregaSubFamilias();
    $('#modalAgregarCatArticulo').modal('hide');
}


// se muestran los modales para desactivar los catalogos dependiendo de la opcion
//pueden ser una familia,subfamilia, codigos contables o catalogo de articulos
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
    
    else if (strUser === "4") {


        $.ajax({type: "GET",
            url: "api/contables/" + filtro,
            success: desactivarCatConta,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}



//se muestra el modal correpondiente para activar/desactivar una familia

var famidAct;
var famiDesAct;

function desactivarFamilia(fam) {

    famidAct = fam.famiIdPk;
    famiDesAct = fam.famiDesc;
    // famiEstAct = fam.famiEstado;
    if (fam.famiEstado === '1') {
        $('#modalDesactivar').modal('show');
    } else {
        $('#modalActivar').modal('show');
    }
}


//*********
function ActivarFamilia(fam) {
    famidAct = fam.famiIdPk;
    famiDesAct = fam.famiDesc;
//famiEstAct = fam.famiEstado;
    $('#modalDesactivar').modal('show');
}

var subFamidAct;
var subFamiFami;
var subFamiDesAct;
//se muestra el modal correpondiente para activar/desactivar una subfamilia

function desactivarSubFamilia(Subfam) {
    subFamidAct = Subfam.subFamiIdPk;
    subFamiFami = Subfam.sboTbFamilia;
    subFamiDesAct = Subfam.subFamiDesc;
    // subFamiEstAct=Subfam.subFamiEstado;
    if (Subfam.subFamiEstado === '1') {
        $('#modalDesactivar').modal('show');
    } else {
        $('#modalActivar').modal('show');
    }
}
var catArtId;
var catArtDesc;
var catArtSubF;
var catArtEst;
//se muestra el modal correpondiente para activar/desactivar un articulo

function desactivarCatArt(articulo) {
    catArtId = articulo.catIdPk;
    catArtDesc = articulo.catDesc;
    catArtSubF = articulo.sboTbSubFamilia;
   // catArtEst = articulo.artCat_Estado;

    if (articulo.artCat_Estado === '1') {
        $('#modalDesactivar').modal('show');
    } else {
        $('#modalActivar').modal('show');
    }
}

var catContId;
var catContDesc;
var catCodi;
var catContEst;
var catNivel;

//se muestra el modal correpondiente para activar/desactivar un codigo contable

function desactivarCatConta(cont) {
    catContId = cont.cntIdPk;
    catContDesc = cont.cntDesc;
    catCodi = cont.cntCodi;
   // catContEst = cont.cntEst;
    catNivel= cont.cntNivel;

    if (cont.cntEst === '1') {
        $('#modalDesactivar').modal('show');
    } else {
        $('#modalActivar').modal('show');
    }
}

//se actualiza el estado de las familias para hacer el bloqueo/desbloqueo de esta
var famiEstAct = 0;
function actualizarEstadoFamilia() {

    SboTbFamilia = {
        famiIdPk: famidAct,
        famiDesc: famiDesAct,
        famiEstado: famiEstAct

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
//se oculta el modal despues de actualizar el estado de la familia
function afterUpdateFmEs() {
    buscarFamilias();
    $('#modalDesactivar').modal('hide');

}

//se actualiza el estado de las subfamilias para hacer el bloqueo/desbloqueo de esta

var subFamiEstAct = 0;
function actualizarEstadoSubFamilia() {
//var e = document.getElementById("selectFamilias");
//var strUser = e.options[e.selectedIndex].value;

    SboTbSubFamilia = {
        subFamiIdPk: subFamidAct,
        subFamiDesc: subFamiDesAct,
        sboTbFamilia: subFamiFami,
        subFamiEstado: subFamiEstAct
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
//se oculta el modal despues de hacer el update del estado
function afterUpdateSubFmEs() {
    buscarSubFamilias();
    $('#modalDesactivar').modal('hide');

}

//se actualiza el estado del articulo para hacer el bloqueo/desbloqueo de esta

var EstadoActual = 0;
function actualizarEstadoCatArticulo() {

    SboTbCatArticulo = {
        catDesc: catArtDesc,
        catIdPk: catArtId,
        artCat_Estado: EstadoActual,
        sboTbSubFamilia: catArtSubF
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

//se oculta el modal depues de actualizar el estado del articulo
function afterUpdateCatArtEs() {
    buscarCatArticulos();
    $('#modalDesactivar').modal('hide');

}


//se actualiza el estado del codigo contable para hacer el bloqueo/desbloqueo de esta

var EstadoActual = 0;
function actualizarEstadoCatConta() {

    SboTbCatContable = {
        cntDesc: catContDesc,
        cntIdPk: catContId,
        cntEst: EstadoActual,
        cntCodi: catCodi,
        cntNivel:catNivel
    };
    $.ajax({type: "PUT",
        url: "api/contables",
        data: JSON.stringify(SboTbCatContable),
        contentType: "application/json",
        success: afterUpdateCatContEs,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
//se oculta el modal despues de hacer el update del codigo contable
function afterUpdateCatContEs() {
    buscarCatContables();
    $('#modalDesactivar').modal('hide');

}

//se desactiva el registro del catalogo dependiendo de la opcion seleccionada
//puede ser una familia,subfamilia, codigo contable o un articulo
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
    else if (strUser === "4") {


        $.ajax({type: "GET",
            url: "api/contables/",
            success: actualizarEstadoCatConta,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}
//se activa el registro del catalogo dependiendo de la opcion seleccionada
//puede ser una familia,subfamilia, codigo contable o un articulo
function Activar() {
    var e = document.getElementById("selectcatalogos");
    var strUser = e.options[e.selectedIndex].value;

    if (strUser === "1") {
        $.ajax({type: "GET",
            url: "api/familias/",
            success: ActivaFamilia,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    } else if (strUser === "2") {
        $.ajax({type: "GET",
            url: "api/subfamilias/",
            success: ActivaSubF,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });

    } else if (strUser === "3") {


        $.ajax({type: "GET",
            url: "api/catArticulos/",
            success: activarArticulo,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
    else if (strUser === "4") {


        $.ajax({type: "GET",
            url: "api/contables/",
            success: ActivaContables,
            error: function (jqXHR) {
                alert(errorMessage(jqXHR.status));
            }
        });
    }
}
//aca se hace la actualizacion del estado en la familia seleccionada para activarla
function ActivaFamilia() {

    var valor = 1;
    SboTbFamilia = {
        famiIdPk: famidAct,
        famiDesc: famiDesAct,
        famiEstado: valor
    };
    $.ajax({type: "PUT",
        url: "api/familias",
        data: JSON.stringify(SboTbFamilia),
        contentType: "application/json",
        success: ocultarModalActivarF,
        error: function (jqXHR) {
            alert('Error');
        }
    });


}
//se oculta el modal de activar familia
function ocultarModalActivarF() {
    buscarFamilias();
    $('#modalActivar').modal('hide');

}
//aca se hace la actualizacion del estado en el codigo contable seleccionado para activarlo
function ActivaContables() {

    var valor = 1;
   SboTbCatContable = {
        cntDesc: catContDesc,
        cntIdPk: catContId,
        cntEst: valor,
        cntCodi: catCodi,
        cntNivel:catNivel
    };
    $.ajax({type: "PUT",
        url: "api/contables",
        data: JSON.stringify(SboTbCatContable),
        contentType: "application/json",
        success: ocultarModalActivarCont,
        error: function (jqXHR) {
            alert('Error');
        }
    });


}
//se oculta el modal de activar codigo contable
function ocultarModalActivarCont() {
    buscarCatContables();
    $('#modalActivar').modal('hide');

}
//aca se hace la actualizacion del estado en la subfamilia seleccionada para activarla

function ActivaSubF() {
    var valor = 1;
    SboTbSubFamilia = {
        subFamiIdPk: subFamidAct,
        subFamiDesc: subFamiDesAct,
        sboTbFamilia: subFamiFami,
        subFamiEstado: valor
    };
    $.ajax({type: "PUT",
        url: "api/subfamilias",
        data: JSON.stringify(SboTbSubFamilia),
        contentType: "application/json",
        success: ocultarModalActivarSubF,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
//se oculta el modal de activar subfamilia
function ocultarModalActivarSubF() {
    buscarSubFamilias();
    $('#modalActivar').modal('hide');

}

//aca se hace la actualizacion del estado en el articulo seleccionado para activarlo

function activarArticulo() {
    var valor = 1;
    SboTbCatArticulo = {
        catDesc: catArtDesc,
        catIdPk: catArtId,
        artCat_Estado: valor,
        sboTbSubFamilia: catArtSubF
    };
    $.ajax({type: "PUT",
        url: "api/catArticulos",
        data: JSON.stringify(SboTbCatArticulo),
        contentType: "application/json",
        success: ocultarModalActivarCatArt,
        error: function (jqXHR) {
            alert('Error');
        }
    });

}
//se oculta el modal para activar el articulo
function ocultarModalActivarCatArt() {
    buscarCatArticulos();
    $('#modalActivar').modal('hide');

}


$(document).ready(function () {
    logged();
});