package com.datos.clientes.Controlador;
import com.datos.clientes.Modelo.Clientes;
import com.datos.clientes.Servicio.ServicioClientes;
import com.datos.clientes.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class authControlador {

    @Autowired
    private ServicioClientes servicioClientes;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Clientes cliente){

        Clientes clienteLogueado=servicioClientes.obtenerClientePorCredendiales(cliente);
        if (clienteLogueado != null){

            String tokenJwt = jwtUtil.create(String.valueOf(clienteLogueado.getId()), clienteLogueado.getCorreo());

            return tokenJwt;

        }
        return "fail";

    }


    }






