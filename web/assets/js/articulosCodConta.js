function listarArt(personas) {
    var listado = $("#listaArticulos");
    listado.html("");
    personas.forEach((p) => {
        filaArt(listado, p);
    });
}

function filaArt(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.artDesc + "</td>"
            + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiIdPk + "</td>"
            + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiDesc + "</td>"
            + "<td><img src='assets/img/plus.png' onclick='abrirModalCodContable(\""+ objeto.artIdPk + "\");'></td>");
    listado.append(tr);
}

function art() {
    $.ajax({type: "GET",
        url: "api/articulos/codConta?filtro=" + $("#filtro").val(),
        success: listarArt
    });
}

var articuloID;
function abrirModalCodContable(id) {
    $('#modalCodContable').modal('show');
    articuloID = id;
    selectConta();
}

function selectConta() {
    $.ajax({type: "GET",
        url: "api/contables?filtro=" + " ",
        success: function (data) {
            $.each(data, function (key, c) {
                $("#selectConta").append('<option value=' + c.cntIdPk + '>' + '➤ ' + c.cntCodi + '-' + c.cntDesc + '</option>');
            });
        },
        error: function (data) {
            alert('error');
        }
    });
}

$(document).ready(function () {
    logged();
});

function asignarCodCont() {
    var codConta = document.getElementById("selectConta").value;
    SboTbArticulo = {
        artIdPk: articuloID,
        artCodiCont: codConta
    };
    $.ajax({type: "PUT",
        url: "api/articulos/putCodConta",
        data: JSON.stringify(SboTbArticulo),
        contentType: "application/json",
        success: limpiar,
        error: function (jqXHR) {
            alert(errorMessage(jqXHR.status));
        }
    });
    $('#modalCodContable').modal('hide');
    
}

function limpiar() {
    $('#formCodContable').trigger("reset");
    art();
}
