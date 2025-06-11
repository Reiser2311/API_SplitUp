package com.splitup.crud.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipantePagoId implements Serializable {

    @Column(name = "participante_id")
    private Integer participanteId;

    @Column(name = "pago_id")
    private Integer pagoId;

    public ParticipantePagoId() {}

    public ParticipantePagoId(Integer participanteId, Integer pagoId) {
        this.participanteId = participanteId;
        this.pagoId = pagoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (!(o instanceof ParticipantePagoId))  return false;
        ParticipantePagoId that = (ParticipantePagoId) o;
        return Objects.equals(participanteId, that.participanteId) &&
                Objects.equals(pagoId, that.pagoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participanteId, pagoId);
    }

    public Integer getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Integer participanteId) {
        this.participanteId = participanteId;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer splitId) {
        this.pagoId = splitId;
    }
}
