/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//funcion para aumentar el tamaño de la letra de la ventana
function increaseFontSizeBy1px() {
    txt = document.getElementById('body');
    style = window.getComputedStyle(txt, null).getPropertyValue('font-size');
    currentSize = parseFloat(style);
    txt.style.fontSize = (currentSize + 1) + 'px';
}
//funcion para disminuir el tamaño de la letra de la ventana
function decreaseFontSizeBy1px() {
    txt = document.getElementById('body');
    style = window.getComputedStyle(txt, null).getPropertyValue('font-size');
    currentSize = parseFloat(style);
    txt.style.fontSize = (currentSize - 1) + 'px';
}

