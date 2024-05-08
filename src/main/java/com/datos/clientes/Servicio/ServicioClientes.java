package com.datos.clientes.Servicio;

import com.datos.clientes.Modelo.Clientes;

import java.util.List;

public interface ServicioClientes {

    List<Clientes> getClientes();
    void registroCliente(Clientes cliente);
    void eliminarCliente(Long id);
    public boolean servicioVerificarCorreo(String correo);
    public List<Clientes> obtenerCliente(String correo);
    Clientes obtenerClientePorCredendiales(Clientes cliente);
    void modificarCliente(Clientes cliente);
}
