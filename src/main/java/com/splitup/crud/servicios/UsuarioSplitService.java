package com.splitup.crud.servicios;

import com.splitup.crud.entidades.*;
import com.splitup.crud.repositorio.SplitRepository;
import com.splitup.crud.repositorio.UsuarioRepository;
import com.splitup.crud.repositorio.UsuarioSplitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSplitService {
    private final UsuarioSplitRepository usuarioSplitRepository;
    private final UsuarioRepository usuarioRepositorio;
    private final SplitRepository splitRepositorio;

    public UsuarioSplitService(UsuarioSplitRepository usuarioSplitRepository,
                                UsuarioRepository usuarioRepositorio,
                                SplitRepository splitRepositorio) {
        this.usuarioSplitRepository = usuarioSplitRepository;
        this.usuarioRepositorio = usuarioRepositorio;
        this.splitRepositorio = splitRepositorio;
    }

    public UsuarioSplit asociarUsuarioConSplit(Integer usuarioId, Integer splitId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElseThrow();
        Split split = splitRepositorio.findById(splitId).orElseThrow();

        UsuarioSplit relacion = new UsuarioSplit(usuario, split);
        return usuarioSplitRepository.save(relacion);
    }

    public List<UsuarioSplit> obtenerSplitsDeUsuario(Integer usuarioId) {
        return usuarioSplitRepository.findByUsuarioId(usuarioId);
    }

    public List<UsuarioSplit> obtenerUsuariosDeSplit(Integer splitId) {
        return usuarioSplitRepository.findBySplitId(splitId);
    }

    public void eliminarRelacion(Integer splitId, Integer usuarioId) {
        UsuarioSplitId id = new UsuarioSplitId(usuarioId, splitId);

        if (usuarioSplitRepository.existsById(id)) {
            usuarioSplitRepository.deleteById(id);

            // Comprobar si quedan más relaciones con ese split
            long count = usuarioSplitRepository.countByIdSplitId(splitId);
            if (count == 0) {
                splitRepositorio.deleteById(splitId);
            }
        } else {
            throw new RuntimeException("Relación no encontrada");
        }
    }

}
