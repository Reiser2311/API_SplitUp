package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.servicios.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/splits")
public class SplitController {
    private final SplitService splitService;

    @Autowired
    public SplitController(SplitService splitService) {
        this.splitService = splitService;
    }

    @GetMapping
    public List<Split> getSplits() {
        return splitService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Split> getSplitById(@PathVariable Integer id) {
        Optional<Split> split = splitService.findById(id);
        return split.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("usuarios/{correo}")
    public ResponseEntity<List<Split>> getSplitsByUsuarioId(@PathVariable Integer id) {
        List<Split> splits = splitService.findByUsuarioId(id);
        if (splits.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(splits);
    }

    @PostMapping
    public Split createSplit(@RequestBody Split split) {
        if (split.getUsuario() == null) {
            throw new RuntimeException("El usuario del Split no puede ser nulo");
        }

        return splitService.save(split);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSplit(@PathVariable Integer id, @RequestParam String titulo) {
        Optional<Split> splitOpt = splitService.findById(id);

        if (splitOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        splitService.updateSplit(id, titulo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSplit(@PathVariable Integer id) {
        splitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

