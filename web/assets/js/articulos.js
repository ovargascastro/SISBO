function listarArticulos(personas) {
    var listado = $("#listaArticulos");
    listado.html("");
    personas.forEach((p) => {
        filaArticulos(listado, p);
    });
}

function filaArticulos(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.artDesc + "</td>"
            + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiIdPk + "</td>"
            + "<td>" + objeto.sboTbCatArticulo.sboTbSubFamilia.subFamiDesc + "</td>"
            + "<td><img src='assets/img/plus.png' onclick='abrirModal()'></td>");
    listado.append(tr);
}

function articulos() {

    $.ajax({type: "GET",
        url: "api/articulos/codConta?filtro=" + $("#filtro").val(),
        success: listarArticulos
    });
}

function abrirModal() {
    $('#asignarCodido').modal('show');
}

$(document).ready(function () {
    logged();
});

