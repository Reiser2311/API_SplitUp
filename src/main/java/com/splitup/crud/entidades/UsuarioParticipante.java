package com.splitup.crud.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_participante")
public class UsuarioParticipante {

    @EmbeddedId
    private UsuarioParticipanteId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Usuario usuario;

    @ManyToOne
    @MapsId("participanteId")
    @JoinColumn(name = "participante_id", nullable = false)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Participante participante;


    public UsuarioParticipante() {}

    public UsuarioParticipante(Usuario usuario, Participante participante) {
        this.usuario = usuario;
        this.participante = participante;
        this.id = new UsuarioParticipanteId(usuario.getId(), participante.getId());
    }

    public UsuarioParticipanteId getId() {
        return id;
    }

    public void setId(UsuarioParticipanteId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}

