package com.splitup.crud.controlador;

import com.splitup.crud.entidades.UsuarioParticipante;
import com.splitup.crud.servicios.UsuarioParticipanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario_participante")
public class UsuarioParticipanteController {

    private final UsuarioParticipanteService usuarioParticipanteService;

    public UsuarioParticipanteController(UsuarioParticipanteService usuarioParticipanteService) {
        this.usuarioParticipanteService = usuarioParticipanteService;
    }

    @PostMapping
    public UsuarioParticipante asociarUsuarioConParticipante(@RequestBody RelacionRequest request) {
        return usuarioParticipanteService.asociarUsuarioConParticipante(request.getUsuarioId(), request.getParticipanteId());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<UsuarioParticipante> obtenerParticipantesDeUsuario(@PathVariable Integer usuarioId) {
        return usuarioParticipanteService.obtenerParticipantesDeUsuario(usuarioId);
    }

    @GetMapping("/participante/{participanteId}")
    public UsuarioParticipante obtenerUsuarioDeParticipante(@PathVariable Integer participanteId) {
        return usuarioParticipanteService.obtenerUsuarioDeParticipante(participanteId);
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
