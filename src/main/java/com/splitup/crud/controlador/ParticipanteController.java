package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Participante;
import com.splitup.crud.servicios.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {
    private final ParticipanteService participanteService;

    @Autowired
    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping
    public List<Participante> getParticipantes() {
        return participanteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getParticipanteById(@PathVariable Integer id) {
        Optional<Participante> participante = participanteService.findById(id);
        return participante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public Participante create(@RequestBody Participante participante) {
        return participanteService.save(participante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParticipante(@PathVariable Integer id, @RequestParam String nombre, @RequestParam String correo) {
        Optional<Participante> participante = participanteService.findById(id);

        if (participante.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        participanteService.updateParticipante(id, nombre, correo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Integer id) {
        participanteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
