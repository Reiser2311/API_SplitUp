package com.splitup.crud.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "participante_pago")
public class ParticipantePago {

    @EmbeddedId
    private ParticipantePagoId id;

    @ManyToOne
    @MapsId("participanteId")
    @JoinColumn(name = "participante_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Participante participante;

    @ManyToOne
    @MapsId("pagoId")
    @JoinColumn(name = "pago_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pago pago;

    public ParticipantePago() {}

    public ParticipantePago(Participante participante, Pago pago) {
        this.participante = participante;
        this.pago = pago;
    }

    public ParticipantePagoId getId() {
        return id;
    }

    public void setId(ParticipantePagoId id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
