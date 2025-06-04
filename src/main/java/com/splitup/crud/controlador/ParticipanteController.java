package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Participante;
import com.splitup.crud.entidades.Split;
import com.splitup.crud.repositorio.SplitRepository;
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
    private final SplitRepository splitRepository;

    @Autowired
    public ParticipanteController(ParticipanteService participanteService, SplitRepository splitRepository) {
        this.participanteService = participanteService;
        this.splitRepository = splitRepository;
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

    @GetMapping("/splits/{id}")
    public ResponseEntity<List<Participante>> getParticipantesBySplitId(@PathVariable Integer id) {
        List<Participante> participantes = participanteService.findBySplitId(id);
        if (participantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(participantes);
    }

    @PostMapping
    public ResponseEntity<Participante> createParticipante(@RequestBody Participante participante) {
        if (participante.getId() == null || participante.getSplit().getId() == null) {
            System.out.println("Error: Participante es nulo o el id es nulo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //Split no proporcionado o invalido
        }

        Optional<Split> optionalSplit =  splitRepository.findById(participante.getSplit().getId());
        if (optionalSplit.isEmpty()) {
            System.out.println("Error: No se encontró Split con id " + participante.getSplit().getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //No se encuentra el Split
        }

        participante.setSplit(optionalSplit.get());
        Participante savedParticipante = participanteService.save(participante);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParticipante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParticipante(@PathVariable Integer id, @RequestParam String nombre) {
        Optional<Participante> participante = participanteService.findById(id);

        if (participante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        participanteService.updateParticipante(id, nombre);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Integer id) {
        participanteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
