package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    List<Participante> findBySplitId (Integer splitId);
}
