$(document).ready(function() {
    // on ready
 });
 
 
 async function logear() {
   let datos = {};
   datos.correo = document.getElementById('inputCorreo').value;
   datos.password = document.getElementById('inputPassword').value;





   var validEmail =  /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
   if (!validEmail.test(datos.correo)){
     alert("El correo no es valido")
     return;
   }
 
 
 
 
 
     // Verificar si los campos están llenos
     if (datos.correo === "" || datos.password === "") {
       alert("Por favor, complete todos los campos.");
       return; // Detener el envío del formulario si falta algún campo
   }



 
   const request = await fetch('api/login', {
     method: 'POST',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
     body: JSON.stringify(datos)
   });
   


   const respuesta = await request.text();
   console.log(respuesta)
   if (respuesta != 'fail') {
     localStorage.token = respuesta;
     localStorage.email = datos.correo;
     window.location.href = 'index.html';
   } else {
     alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
   }
 
 }
 