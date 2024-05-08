package com.datos.clientes.Modelo;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity(name= "clientes") // Marcamos la clase para que represente a la tabla mysql
@Table(name= "clientes")
@ToString @EqualsAndHashCode
public class Clientes {

    @Id
    @Getter @Setter @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "correo")
    private String correo;

    @Getter @Setter @Column(name = "telefono")
    private  String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;


}
