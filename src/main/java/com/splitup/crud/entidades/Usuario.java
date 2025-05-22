package com.splitup.crud.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.splitup.crud.servicios.UsuarioService;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @Column(nullable = false, unique = true)
    private Integer id;
    private String correo;
    private String nombre;
    private String contrasenya;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UsuarioSplit> usuarioSplits = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<UsuarioSplit> getUsuarioSplits() {
        return usuarioSplits;
    }

    public void setUsuarioSplits(List<UsuarioSplit> usuarioSplits) {
        this.usuarioSplits = usuarioSplits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
