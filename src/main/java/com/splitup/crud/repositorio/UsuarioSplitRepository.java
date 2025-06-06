package com.splitup.crud.repositorio;

import com.splitup.crud.entidades.UsuarioSplit;
import com.splitup.crud.entidades.UsuarioSplitId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioSplitRepository extends JpaRepository<UsuarioSplit, UsuarioSplitId> {
    List<UsuarioSplit> findByUsuarioId (Integer idUsuario);
    List<UsuarioSplit> findBySplitId (Integer idSplit);
    boolean existsBySplitId (Integer idSplit);
}
