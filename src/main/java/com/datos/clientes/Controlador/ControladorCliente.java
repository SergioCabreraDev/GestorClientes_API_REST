package com.datos.clientes.Controlador;

import com.datos.clientes.Modelo.Clientes;
import com.datos.clientes.Servicio.ServicioClientes;
import com.datos.clientes.Servicio.ServicioClientesImp;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.datos.clientes.utils.JWTUtil;


import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController //Marcamos la clase como controlador
public class ControladorCliente {

    @Autowired
    private ServicioClientes servicioClientes;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value ="api/clientes")
    public ResponseEntity<List<Clientes>> getClientes(@RequestHeader(value = "Authorization", required = false) String tokenJwt) {
        if (tokenJwt == null || tokenJwt.isEmpty() || !tokenJwt.contains(".")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        }

        String clienteId = jwtUtil.getKey(tokenJwt);

        if (clienteId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        }

        List<Clientes> clientes = servicioClientes.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @RequestMapping(value ="api/modificar/{correo}", method = RequestMethod.GET)
    public List<Clientes> getCliente(@PathVariable String correo){

       return servicioClientes.obtenerCliente(correo);

    }

    @RequestMapping(value = "api/modificar",  method = RequestMethod.PUT)
    public void modificarCliente(@RequestBody Clientes cliente){

        servicioClientes.modificarCliente(cliente);

    }

    @RequestMapping(value ="api/clientes/{id}", method = RequestMethod.DELETE)
    public void eliminarCliente(@PathVariable Long id){
        servicioClientes.eliminarCliente(id);
    }


    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    public ResponseEntity<String> registroCliente(@RequestBody Clientes cliente) {
        // Verificar si el correo ya está registrado
        boolean correoExiste = servicioClientes.servicioVerificarCorreo(cliente.getCorreo());

        if (correoExiste) {
            // Devolver un indicador de error al cliente
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        } else {
            // Si el correo no está registrado, realizar el registro del cliente
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1, 1024, 1, cliente.getPassword());
            cliente.setPassword(hash);

            servicioClientes.registroCliente(cliente);

            // Devolver una respuesta exitosa al cliente
            return ResponseEntity.ok("Cliente registrado correctamente");
        }
    }


}
