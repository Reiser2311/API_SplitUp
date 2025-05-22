package com.splitup.crud.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioSplitId implements Serializable {

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "split_id")
    private Integer splitId;

    public UsuarioSplitId() {}

    public UsuarioSplitId(Integer usuarioId, Integer splitId) {
        this.usuarioId = usuarioId;
        this.splitId = splitId;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioSplitId)) return false;
        UsuarioSplitId that = (UsuarioSplitId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(splitId, that.splitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, splitId);
    }

    // Getters y setters
    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getSplitId() {
        return splitId;
    }

    public void setSplitId(Integer splitId) {
        this.splitId = splitId;
    }
}
