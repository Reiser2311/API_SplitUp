package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Participante;
import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.entidades.UsuarioParticipante;
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

    public List<UsuarioParticipante> obtenerParticipantesDeUsuario(Integer usuarioId) {
        return usuarioParticipanteRepository.findByIdUsuarioId(usuarioId);
    }

    public UsuarioParticipante obtenerUsuarioDeParticipante(Integer participanteId) {
        return usuarioParticipanteRepository.findByIdParticipanteId(participanteId);
    }


}
