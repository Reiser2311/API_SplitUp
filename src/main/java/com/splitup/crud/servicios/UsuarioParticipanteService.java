package com.splitup.crud.servicios;

import com.splitup.crud.entidades.*;
import com.splitup.crud.repositorio.ParticipantePagoRepository;
import com.splitup.crud.repositorio.ParticipanteRepository;
import com.splitup.crud.repositorio.UsuarioParticipanteRepository;
import com.splitup.crud.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioParticipanteService {
    private final UsuarioParticipanteRepository usuarioParticipanteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParticipanteRepository participanteRepository;

    public UsuarioParticipanteService(UsuarioParticipanteRepository usuarioParticipanteRepository,
                                      UsuarioRepository usuarioRepository,
                                      ParticipanteRepository participanteRepository) {
        this.usuarioParticipanteRepository = usuarioParticipanteRepository;
        this.usuarioRepository = usuarioRepository;
        this.participanteRepository = participanteRepository;
    }

    public UsuarioParticipante asociarUsuarioConParticipante (Integer usuarioId, Integer participanteId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow();
        Participante participante = participanteRepository.findById(participanteId).orElseThrow();

        UsuarioParticipante relacion = new UsuarioParticipante(usuario, participante);
        return usuarioParticipanteRepository.save(relacion);
    }

    public Boolean comprobarRelacion(Integer usuarioId, Integer participanteId) {
        UsuarioParticipanteId id = new UsuarioParticipanteId(usuarioId, participanteId);
        return usuarioParticipanteRepository.existsById(id);
    }

    public List<UsuarioParticipante> obtenerParticipantesDeUsuario(Integer usuarioId) {
        return usuarioParticipanteRepository.findByIdUsuarioId(usuarioId);
    }

    public UsuarioParticipante obtenerUsuarioDeParticipante(Integer participanteId) {
        return usuarioParticipanteRepository.findByIdParticipanteId(participanteId);
    }

    public void eliminarRelacion(Integer participanteId, Integer usuarioId) {
        UsuarioParticipanteId id = new UsuarioParticipanteId(usuarioId, participanteId);
        if (usuarioParticipanteRepository.existsById(id)) {
            usuarioParticipanteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Relacion no encontrado");
        }
    }


}
