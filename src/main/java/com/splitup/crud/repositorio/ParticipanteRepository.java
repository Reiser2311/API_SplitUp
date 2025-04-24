package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
}
