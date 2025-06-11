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
    public ResponseEntity<ParticipantePago> asociarParticipanteConPago(@RequestBody RelacionRequest request){
        ParticipantePago savedParticipantePago = participantePagoService.asociarParticipanteConSplit(request.participanteId, request.pagoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParticipantePago);
    }

    @GetMapping("/participante/{pagoId}")
    public ResponseEntity<List<Participante>> obtenerPaticipantesPorPago(@PathVariable Integer splitId){
        List<ParticipantePago> relaciones = participantePagoService.obtenerParticipantesDePago(splitId);
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

    @GetMapping("/ids_participantes_por_pago/{pagoId}")
    public ResponseEntity<List<Integer>> obtenerIdsParticipantesPorPago(@PathVariable Integer pagoId) {
        List<ParticipantePago> relaciones = participantePagoService.obtenerParticipantesDePago(pagoId);
        List<Integer> ids = relaciones.stream()
                .map(r -> r.getParticipante().getId())
                .collect(Collectors.toList());
        return ResponseEntity.ok(ids);
    }

    @DeleteMapping("/{participanteId}/{pagoId}")
    public ResponseEntity<Void> eliminarParticipante(@PathVariable Integer participanteId, @PathVariable Integer pagoId){
        try {
            participantePagoService.eliminarRelacion(participanteId, pagoId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public static class RelacionRequest {
        private Integer participanteId;
        private Integer pagoId;

        public Integer getParticipanteId() {
            return participanteId;
        }

        public void setParticipanteId(Integer participanteId) {
            this.participanteId = participanteId;
        }

        public Integer getPagoId() {
            return pagoId;
        }

        public void setPagoId(Integer pagoId) {
            this.pagoId = pagoId;
        }
    }
}
