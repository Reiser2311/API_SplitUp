package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.entidades.Usuario;
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

    @GetMapping("/usuario/{splitId}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorSplit(@PathVariable Integer splitId) {
//        return usuarioSplitService.obtenerUsuariosDeSplit(splitId);
        List<UsuarioSplit> relacion = usuarioSplitService.obtenerUsuariosDeSplit(splitId);
        List<Usuario> usuarios = relacion.stream()
                .map(UsuarioSplit::getUsuario)
                .collect(Collectors.toList());
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

    @DeleteMapping("/{splitId}/{usuarioId}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable Integer splitId, @PathVariable Integer usuarioId){
        try {
            usuarioSplitService.eliminarRelacion(splitId, usuarioId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
