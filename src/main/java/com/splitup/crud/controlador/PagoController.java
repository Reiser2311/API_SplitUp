package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Pago;
import com.splitup.crud.entidades.Split;
import com.splitup.crud.repositorio.SplitRepository;
import com.splitup.crud.servicios.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    private final PagoService pagoService;
    private final SplitRepository splitRepository;

    @Autowired
    public PagoController(PagoService pagoService, SplitRepository splitRepository) {
        this.pagoService = pagoService;
        this.splitRepository = splitRepository;
    }

    @GetMapping
    public List<Pago> getPagos() {
        return pagoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Integer id) {
        Optional<Pago> pago = pagoService.findById(id);
        return pago.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/splits/{id}")
    public ResponseEntity<List<Pago>> getPagoBySplitId(@PathVariable Integer id) {
        List<Pago> pagos = pagoService.findBySplitId(id);
        return ResponseEntity.ok(pagos);
    }

    @PostMapping
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago) {
        if (pago.getSplit() == null || pago.getSplit().getId() == null) {
            System.out.println("Error: Split es nulo o el id es nulo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //Split no proporcionado o invalido
        }

        Optional<Split> optionalSplit = splitRepository.findById(pago.getSplit().getId());
        if (optionalSplit.isEmpty()) {
            System.out.println("Error: No se encontró Split con id " + pago.getSplit().getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //No se encuentra el Split
        }

        pago.setSplit(optionalSplit.get());
        Pago savedPago = pagoService.save(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPago);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSplit(@PathVariable Integer id, @RequestParam String titulo, @RequestParam Double importe, @RequestParam int pagadoPor) {
        Optional<Pago> pagoOpt = pagoService.findById(id);

        if (pagoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        pagoService.updatePago(id, titulo, importe, pagadoPor);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Integer id) {
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
