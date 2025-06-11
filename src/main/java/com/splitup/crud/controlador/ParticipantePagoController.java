package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Pago;
import com.splitup.crud.entidades.Participante;
import com.splitup.crud.entidades.ParticipantePago;
import com.splitup.crud.servicios.ParticipantePagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participante_pago")
public class ParticipantePagoController {

    private final ParticipantePagoService participantePagoService;

    public ParticipantePagoController(ParticipantePagoService participantePagoService) {
        this.participantePagoService = participantePagoService;
    }

    @PostMapping
    public ResponseEntity<ParticipantePago> asociarParticipanteConSplit(@RequestBody RelacionRequest request){
        ParticipantePago savedParticipantePago = participantePagoService.asociarParticipanteConSplit(request.participanteId,  request.participanteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParticipantePago);
    }

    @GetMapping("/participante/{splitId}")
    public ResponseEntity<List<Participante>> obtenerPaticipantesPorSplit(@PathVariable Integer splitId){
        List<ParticipantePago> relaciones = participantePagoService.obtenerParticipantesDeSplit(splitId);
        List<Participante> participantes = relaciones.stream()
                .map(ParticipantePago::getParticipante)
                .collect(Collectors.toList());

        return ResponseEntity.ok(participantes);
    }

    @GetMapping("/pago/{participanteId}")
    public ResponseEntity<Pago> obtenerPagoDeParticipante(@PathVariable Integer participanteId){
        ParticipantePago relacion = participantePagoService.obtenerPagoDeParticipante(participanteId);
        Pago pago = relacion.getPago();
        return ResponseEntity.ok(pago);
    }

    public static class RelacionRequest {
        private Integer participanteId;
        private Integer splitId;

        public Integer getParticipanteId() {
            return participanteId;
        }

        public void setParticipanteId(Integer participanteId) {
            this.participanteId = participanteId;
        }

        public Integer getSplitId() {
            return splitId;
        }

        public void setSplitId(Integer splitId) {
            this.splitId = splitId;
        }
    }
}
