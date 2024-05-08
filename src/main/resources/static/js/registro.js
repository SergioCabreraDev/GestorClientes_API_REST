$(document).ready(function() {
    // on ready
 });
 
 
 async function registrarCliente() {

   let datos = {};
   datos.nombre = document.getElementById('inputNombre').value;
   datos.apellido = document.getElementById('inputApellido').value;
   datos.correo = document.getElementById('inputCorreo').value;
   datos.telefono = document.getElementById('inputTelefono').value;
   datos.password = document.getElementById('inputPassword').value;
   
 
   let repetirPassword = document.getElementById('inputPassword1').value;
 
   if (repetirPassword != datos.password) {
     alert('La contraseña que escribiste es diferente.');
     return;
   }


       // Verificar si los campos están llenos
       if (datos.nombre === "" || datos.apellido === "" || datos.correo === "" || datos.telefono === "" || datos.password === "") {
        alert("Por favor, complete todos los campos.");
        return; // Detener el envío del formulario si falta algún campo
    }
  

   var validEmail =  /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
  if (!validEmail.test(datos.correo)){
    alert("El correo no es valido")
    return;
  }



   const request = await fetch('api/register', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  
  // Extraer el texto de la respuesta
  const data = await request.text();
  
  // Aquí puedes mostrar la respuesta en algún lugar de tu página
  console.log(data);

  if(data == "El correo ya está registrado"){
    alert("Este Correo Ya Esta Registrado")
    return;
  }



  
   alert("La cuenta fue creada con exito!");
   window.location.href = 'login.html'
 
 }
 