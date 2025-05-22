package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.entidades.UsuarioSplit;
import com.splitup.crud.servicios.UsuarioSplitService;
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
    public UsuarioSplit asociarUsuarioConSplit(@RequestBody RelacionRequest request) {
        return usuarioSplitService.asociarUsuarioConSplit(request.getUsuarioId(), request.getSplitId());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<UsuarioSplit> obtenerSplitsDeUsuario(@PathVariable Integer usuarioId) {
        return usuarioSplitService.obtenerSplitsDeUsuario(usuarioId);
    }

    @GetMapping("/split/{splitId}")
    public List<UsuarioSplit> obtenerUsuariosDeSplit(@PathVariable Integer splitId) {
        return usuarioSplitService.obtenerUsuariosDeSplit(splitId);
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
