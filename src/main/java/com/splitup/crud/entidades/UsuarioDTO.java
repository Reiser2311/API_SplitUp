package com.splitup.crud.entidades;

public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String nombre;
    private String fotoPerfil;

    public UsuarioDTO(Integer id, String correo, String nombre, String fotoPerfil) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
