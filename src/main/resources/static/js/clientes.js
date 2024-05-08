// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarClientes();


});

async function cargarClientes() {
    try {
        const request = await fetch('api/clientes', {
            method: 'GET',
            headers: getHeaders()
        });

        if (!request.ok) {
            throw new Error('Error al obtener la lista de clientes');
        }
        
        const clientes = await request.json();

        let listaHtml ='';
        for(let cliente of clientes){
            let svg ='<img src="/img/icons8-eliminar-48.png" alt="" width="25px">';
            let botonEliminar = '';

            if(cliente.correo === localStorage.email){
                botonEliminar = '<a id="reload" onclick="eliminarClientePropio('+ cliente.id +')" class="tu_usuario">'+ 'Tu usuario' +'</a>';
            } else {
                botonEliminar = '<a href="javascript:location.reload()" id="reload" onclick="eliminarCliente('+ cliente.id +')" class="">'+ svg +'</a>';
            }
        
            let clienteHtml = '<tr><td>'+cliente.nombre+'</td><td>'+cliente.apellido+'</td><td><a href="#">'+cliente.correo+'</a></td><td>'+cliente.telefono+'</td><td>' + botonEliminar + '</td></tr>';
        
            listaHtml += clienteHtml;
        }

        document.querySelector('#clientes tbody').innerHTML = listaHtml.trim();
    } catch (error) {
        console.error('Error en la función cargarClientes:', error);
        // Aquí puedes manejar el error de la forma que desees, por ejemplo, mostrar un mensaje al usuario.
    }
}



function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}


async function eliminarCliente(id) {
  if (!confirm('¿Quiere eliminar este usuario?')) {
      return;
  }
  

  console.log(id);

  try {
      const response = await fetch('api/clientes/' + id, {
          method: 'DELETE',
          headers: getHeaders()
      });

      if (!response.ok) {
          throw new Error('Network response was not ok');
      }

      const clientes = await response.json();

     
  } catch (error) {
      console.error('Error:', error);
  }
}




async function eliminarClientePropio(id) {
    if (!confirm('¿Quiere eliminar su propio usuario?')) {
        return;
    }
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    alert('Has cerrado sesión');
    window.location.href = '../index.html'; // Corregí el error tipográfico aquí
    
    console.log(id);
  
    try {
        const response = await fetch('api/clientes/' + id, {
            method: 'DELETE',
            headers: getHeaders()
        });
  
        if (!response.ok) {
            throw new Error('La respuesta de la red no fue correcta');
        }
  
        const clientes = await response.json();
    } catch (error) {
        console.error('Error:', error);
    }
}





