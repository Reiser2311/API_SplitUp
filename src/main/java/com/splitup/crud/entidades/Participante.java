package com.splitup.crud.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Participante")
public class Participante {
    @Id
    @Column(nullable = false, unique = true)
    private Integer id;
    private String nombre;
    private String correo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
