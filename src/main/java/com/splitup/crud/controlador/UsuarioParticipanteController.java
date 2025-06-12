package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Participante;
import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.entidades.UsuarioParticipante;
import com.splitup.crud.servicios.UsuarioParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario_participante")
public class UsuarioParticipanteController {

    private final UsuarioParticipanteService usuarioParticipanteService;

    public UsuarioParticipanteController(UsuarioParticipanteService usuarioParticipanteService) {
        this.usuarioParticipanteService = usuarioParticipanteService;
    }

    @PostMapping
    public ResponseEntity<UsuarioParticipante> asociarUsuarioConParticipante(@RequestBody RelacionRequest request) {
        UsuarioParticipante savedUsuarioParticipante = usuarioParticipanteService.asociarUsuarioConParticipante(request.usuarioId, request.participanteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuarioParticipante);
    }

    @GetMapping("/participante/{usuarioId}")
    public ResponseEntity<List<Participante>> obtenerParticipantesPorUsuario(@PathVariable Integer usuarioId) {
        List<UsuarioParticipante> relacion = usuarioParticipanteService.obtenerParticipantesDeUsuario(usuarioId);
        List<Participante> participantes = relacion.stream()
                .map(UsuarioParticipante::getParticipante)
                .collect(Collectors.toList());
        return ResponseEntity.ok(participantes);
    }

    @GetMapping("/usuario/{participanteId}")
    public ResponseEntity<Usuario> obtenerUsuarioPorParticipante(@PathVariable Integer participanteId) {
        UsuarioParticipante relacion = usuarioParticipanteService.obtenerUsuarioDeParticipante(participanteId);
        Usuario usuario = relacion.getUsuario();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/existe/{participanteId}/{usuarioId}")
    public ResponseEntity<Boolean> existeUsuarioPorParticipante(@PathVariable Integer participanteId, @PathVariable Integer usuarioId) {
        boolean existe = usuarioParticipanteService.comprobarRelacion(usuarioId, participanteId);
        return ResponseEntity.status(HttpStatus.OK).body(existe);
    }

    @DeleteMapping("/{participanteId}/{usuarioId}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable Integer participanteId, @PathVariable Integer usuarioId){
        try {
            usuarioParticipanteService.eliminarRelacion(participanteId, usuarioId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    public static class RelacionRequest {
        private Integer usuarioId;
        private Integer participanteId;

        public Integer getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Integer usuarioId) {
            this.usuarioId = usuarioId;
        }

        public Integer getParticipanteId() {
            return participanteId;
        }

        public void setParticipanteId(Integer participanteId) {
            this.participanteId = participanteId;
        }
    }
}
