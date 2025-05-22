package com.splitup.crud.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_split")
public class UsuarioSplit {

    @EmbeddedId
    private UsuarioSplitId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("splitId")
    @JoinColumn(name = "split_id")
    private Split split;

    public UsuarioSplit() {}

    public UsuarioSplit(Usuario usuario, Split split) {
        this.usuario = usuario;
        this.split = split;
        this.id = new UsuarioSplitId(usuario.getId(), split.getId());
    }

    public UsuarioSplitId getId() {
        return id;
    }

    public void setId(UsuarioSplitId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }
}
