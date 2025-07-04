package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Pago;
import com.splitup.crud.entidades.Participante;
import com.splitup.crud.entidades.ParticipantePago;
import com.splitup.crud.entidades.ParticipantePagoId;
import com.splitup.crud.repositorio.PagoRepository;
import com.splitup.crud.repositorio.ParticipanteRepository;
import com.splitup.crud.repositorio.ParticipantePagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantePagoService {
    private final ParticipantePagoRepository participantePagoRepository;
    private final ParticipanteRepository participanteRepository;
    private final PagoRepository pagoRepository;

    public ParticipantePagoService(ParticipantePagoRepository participantePagoRepository,
                                   ParticipanteRepository participanteRepository,
                                   PagoRepository pagoRepository) {
        this.participantePagoRepository = participantePagoRepository;
        this.participanteRepository = participanteRepository;
        this.pagoRepository = pagoRepository;
    }

    public ParticipantePago asociarParticipanteConSplit (Integer participanteId, Integer pagoId) {
        Participante participante = participanteRepository.findById(participanteId).orElseThrow();
        Pago pago = pagoRepository.findById(pagoId).orElseThrow();

        ParticipantePago relacion = new ParticipantePago(participante, pago);
        return participantePagoRepository.save(relacion);
    }

    public void eliminarRelacion(Integer participanteId, Integer pagoId) {
        ParticipantePagoId id = new ParticipantePagoId(participanteId, pagoId);
        if (participantePagoRepository.existsById(id)) {
            participantePagoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Relacion no encontrado");
        }
    }

    public List<ParticipantePago> obtenerParticipantesDePago(Integer splitId) {
        return participantePagoRepository.findByIdPagoId(splitId);
    }

    public ParticipantePago obtenerPagoDeParticipante(Integer participanteId) {
        return participantePagoRepository.findByIdParticipanteId(participanteId);
    }
}
