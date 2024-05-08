$(document).ready(function() {
    datosCliente();
 });
 
 
 async function datosCliente() {
    // Obtener el correo del almacenamiento local
    let correoIndex = localStorage.getItem("email");

    const request = await fetch('api/modificar/' + correoIndex, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    const respuesta = await request.json(); // Parsear la respuesta como JSON
    console.log(respuesta); // Imprimir la respuesta en la consola del navegador


    document.getElementById("cliente_id").value = respuesta[0].id;

    document.getElementById("cliente_nombre").value = respuesta[0].nombre;

    document.getElementById("cliente_apellido").value = respuesta[0].apellido;

    document.getElementById("cliente_correo").value = respuesta[0].correo;

    document.getElementById("cliente_telefono").value = respuesta[0].telefono;


    
}


async function actualizarCliente() {

    let id = document.getElementById("cliente_id").value;
    let correo = document.getElementById("cliente_correo").value
    let nombre = document.getElementById("cliente_nombre").value;
    let apellido = document.getElementById("cliente_apellido").value;
    let telefono = document.getElementById("cliente_telefono").value;

           // Verificar si los campos están llenos
           if (nombre === "" || apellido === "" || correo === "" || telefono === "") {
            alert("Por favor, complete todos los campos.");
            return; // Detener el envío del formulario si falta algún campo
        }
      
    
       var validEmail =  /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
      if (!validEmail.test(datos.correo)){
        alert("El correo no es valido")
        return;
      }


    let cliente = {
        id: id,
        correo: correo,
        nombre: nombre,
        apellido: apellido,
        telefono: telefono
    };

    const request = await fetch('api/modificar', {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cliente)
    });

    localStorage.email = correo;

    alert("Se han aplicado los cambios correctamente");
}

