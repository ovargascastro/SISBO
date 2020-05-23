function imprimir() {
    var depto = document.getElementById("SelectDptos").value;
    var bodeg = document.getElementById("SelectBodegas").value;
    if ((depto !== "0") && (bodeg !== "0")) {
        $.ajax({type: "GET",
            url: "api/tomaFisica/" + bodeg + "/" + depto,
            success: lista
        });
        $('#ImprimirReporte').modal('show');
    } else {
        alert("Debe seleccionar los parámetros para poder imprimir el reporte");
    }
    
}

//se listan las existencias en la tabla
function lista(personas) {
    var listado = $("#listaArticulosReporte");
    listado.html("");
    personas.forEach((p) => {
        fila(listado, p);
    });
}

//se utliza para mostrar los datos en las filas de la funcion anterior
function fila(listado, objeto) {
    var tr = $("<tr />");
    tr.html(
            "<td>" + objeto.sboTbBodega.bodeDesc + "</td>"
            + "<td>" + objeto.articulo.abaaTbDepartamento.deptoNomb + "</td>"
            + "<td>" + objeto.articulo.sboSicop.sicopDesc + "</td>"
            + "<td>" + objeto.sboTbEsta + "</td>");
    listado.append(tr);
}

//imprimir JS trabajar desde aqui

document.getElementById('export').addEventListener('click',
        PDF);

var specialElementHandlers = {
    // element with id of "bypass" - jQuery style selector
    '.no-export': function (element, renderer) {
        // true = "handled elsewhere, bypass text extraction"
        return true;
    }
};

var getImageFromUrl = function (url, callback) {
    var img = new Image();
    img.onError = function () {
        alert('Cannot load image: "' + url + '"');
    };
    img.onload = function () {
        callback(img);
    };
    img.src = url;
}

function exportPDF(imgData) {
    var doc = new jsPDF('p', 'pt', 'a4');
    doc.addImage(imgData, 'JPEG', 0, 0, 100, 90);
    var source = document.getElementById('content').innerHTML;
    var margins = {
        top: 80,
        bottom: 10,
        left: 10,
        width: 595
    };

    doc.fromHTML(
            source, // HTML string or DOM elem ref.
            margins.left,
            margins.top, {
                'width': margins.width,
                'elementHandlers': specialElementHandlers
            },
            function (dispose) {
                doc.save('Reporte de Consumo por Departamento.pdf');
            }, margins);
}

function PDF() {
    getImageFromUrl('assets/img/Escudo2.jpg', exportPDF);
}



