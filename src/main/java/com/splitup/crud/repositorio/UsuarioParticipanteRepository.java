package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.UsuarioParticipante;
import com.splitup.crud.entidades.UsuarioParticipanteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioParticipanteRepository extends JpaRepository<UsuarioParticipante, UsuarioParticipanteId> {
    List<UsuarioParticipante> findByIdUsuarioId(Integer idUsuario);
    UsuarioParticipante findByIdParticipanteId(Integer idParticipante);
}
