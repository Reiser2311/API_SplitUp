package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.ParticipantePago;
import com.splitup.crud.entidades.ParticipantePagoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantePagoRepository extends JpaRepository<ParticipantePago, ParticipantePagoId> {
    List<ParticipantePago> findByIdPagoId(Integer idPago);
    ParticipantePago findByIdParticipanteId(Integer idParticipante);
}
