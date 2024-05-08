package com.datos.clientes.Servicio;
import com.datos.clientes.Modelo.Clientes;
import com.datos.clientes.repository.ClienteRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ServicioClientesImp implements ServicioClientes {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ClienteRepository clienteRepository;


    public List<Clientes> getClientes(){

        String query = "FROM clientes";

        return entityManager.createQuery(query).getResultList();

    }



    public void eliminarCliente(Long id){
        Clientes cliente = entityManager.find(Clientes.class, id);
        entityManager.remove(cliente);

    }



    @Override
    public Clientes obtenerClientePorCredendiales(Clientes cliente) {
        String query = "FROM clientes WHERE correo = :correo";
        List<Clientes> lista = entityManager.createQuery(query)
                .setParameter("correo", cliente.getCorreo())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, cliente.getPassword())) {
            return lista.get(0);
        }
        return null;
    }

    public void registroCliente(Clientes cliente){

    entityManager.merge(cliente);

    }

    public boolean servicioVerificarCorreo(String correo){

        Query query = entityManager.createQuery("SELECT COUNT(*) FROM clientes WHERE correo = :correo");

        query.setParameter("correo", correo);

        // Ejecutar la consulta y obtener el resultado
        Long count = (Long) query.getSingleResult();

        // Devolver true si se encuentra el correo, false de lo contrario
        return count > 0;
    }

    public List<Clientes> obtenerCliente(String correo){
        String query = "FROM clientes WHERE correo = :correo";
        return entityManager.createQuery(query)
                .setParameter("correo", correo)
                .getResultList();
    }

    public void modificarCliente(Clientes cliente) {
        // Recuperar el cliente existente de la base de datos
        Clientes clienteExistente = clienteRepository.findById(cliente.getId()).orElse(null);

        if (clienteExistente != null) {
            // Actualizar los campos necesarios del cliente existente
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setCorreo(cliente.getCorreo());
            clienteExistente.setTelefono(cliente.getTelefono());

            // Guardar los cambios en la base de datos
            clienteRepository.save(clienteExistente);
        }
    }




}
