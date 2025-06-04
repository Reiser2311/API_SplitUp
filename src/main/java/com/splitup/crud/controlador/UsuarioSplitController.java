package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.entidades.UsuarioSplit;
import com.splitup.crud.servicios.UsuarioSplitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario_split")
public class UsuarioSplitController {

    private final UsuarioSplitService usuarioSplitService;

    public UsuarioSplitController(UsuarioSplitService usuarioSplitService) {
        this.usuarioSplitService = usuarioSplitService;
    }

    @PostMapping
    public ResponseEntity<UsuarioSplit> asociarUsuarioConSplit(@RequestBody RelacionRequest request) {
//        return usuarioSplitService.asociarUsuarioConSplit(request.getUsuarioId(), request.getSplitId());
        UsuarioSplit savedUsuarioSplit = usuarioSplitService.asociarUsuarioConSplit(request.getUsuarioId(), request.getSplitId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuarioSplit);
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<UsuarioSplit>> obtenerSplitsDeUsuario(@PathVariable Integer usuarioId) {
//        return usuarioSplitService.obtenerSplitsDeUsuario(usuarioId);
        List<UsuarioSplit> splits = usuarioSplitService.obtenerSplitsDeUsuario(usuarioId);
        return ResponseEntity.ok(splits);
    }


    @GetMapping("/split/{splitId}")
    public ResponseEntity<List<UsuarioSplit>> obtenerUsuariosDeSplit(@PathVariable Integer splitId) {
//        return usuarioSplitService.obtenerUsuariosDeSplit(splitId);
        List<UsuarioSplit> usuarios = usuarioSplitService.obtenerUsuariosDeSplit(splitId);
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/splits/{usuarioId}")
    public ResponseEntity<List<Split>> obtenerSplitsPorUsuario(@PathVariable Integer usuarioId) {
        List<UsuarioSplit> relaciones = usuarioSplitService.obtenerSplitsDeUsuario(usuarioId);
        List<Split> splits = relaciones.stream()
                .map(UsuarioSplit::getSplit)
                .collect(Collectors.toList());

        return ResponseEntity.ok(splits);
    }

    public static class RelacionRequest {
        private Integer usuarioId;
        private Integer splitId;

        public Integer getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Integer usuarioId) {
            this.usuarioId = usuarioId;
        }

        public Integer getSplitId() {
            return splitId;
        }

        public void setSplitId(Integer splitId) {
            this.splitId = splitId;
        }
    }
}
