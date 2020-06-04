/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//funcion para realizar el incio de sesion
function login() {

    $.ajax({type: "GET",
        url: "api/login/" + $("#user").val() + "/" + $("#password").val(),
        success: redirigir,
        error: noEncontrado
    });

}
// error que se muestra al ingresar datos incorrectos
function noEncontrado(){
    var user=$("#user").val();
    
    document.getElementById("errorUser").innerHTML = "No se ha podido iniciar sesión, por favor verifique su usuario "+user+"\
    o escriba nuevamente su contraseña";
    $('#loginError').modal('show');
    
}
//se envia a la pagina principal
function redirigir() {

    location.href = "index.jsp"; 

}