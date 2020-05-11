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

    $('#loginError').modal('show');
     swal("Error de inicio de sesión  "+ user +" Contraseña incorrecta!!", "Digite nuevamente el usuario y la contraseña!!", "error");
    
}
//se envia a la pagina principal
function redirigir() {
        swal("Sesion iniciada..!", "Correctamente!!", "success");
    setTimeout(function(){
        window.location.assign("//localhost:8084/SISBO/index.jsp");
    },2000);
   

}