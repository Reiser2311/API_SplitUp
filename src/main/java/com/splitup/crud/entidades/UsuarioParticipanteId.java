package com.splitup.crud.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioParticipanteId implements Serializable {

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "participante_id")
    private Integer participanteId;

    public UsuarioParticipanteId() {}

    public UsuarioParticipanteId(Integer usuarioId, Integer participanteId) {
        this.usuarioId = usuarioId;
        this.participanteId = participanteId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioParticipanteId)) return false;
        UsuarioParticipanteId that = (UsuarioParticipanteId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(participanteId, that.participanteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, participanteId);
    }
}
