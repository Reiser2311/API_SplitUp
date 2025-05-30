package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Participante;
import com.splitup.crud.repositorio.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository;

    @Autowired
    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<Participante> findAll() {
        return participanteRepository.findAll();
    }

    public Optional<Participante> findById(Integer id) {
        return participanteRepository.findById(id);
    }

    public List<Participante> findBySplitId (Integer splitId) {
        return participanteRepository.findBySplitId(splitId);
    }

    public Participante save(Participante participante) {
        return participanteRepository.save(participante);
    }

    public void deleteById(Integer id) {
        participanteRepository.deleteById(id);
    }

    public void updateParticipante(Integer id, String nombre, String correo) {
        Participante participante = participanteRepository.findById(id).orElseThrow(() -> new RuntimeException("Participante no encontrado"));

        participante.setNombre(nombre);
        participante.setCorreo(correo);

        participanteRepository.save(participante);
    }
}
