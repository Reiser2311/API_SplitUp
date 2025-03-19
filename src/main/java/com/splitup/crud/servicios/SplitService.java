package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.repositorio.SplitRepository;
import com.splitup.crud.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SplitService {
    private final SplitRepository splitRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public SplitService(SplitRepository splitRepository, UsuarioRepository usuarioRepository) {
        this.splitRepository = splitRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Split> findAll() {
        return splitRepository.findAll();
    }

    public Optional<Split> findById(Integer id) {
        return splitRepository.findById(id);
    }

    public Split save(Split split) {

        if (split.getId() == null || split.getUsuario().getCorreo() == null) {
            throw new RuntimeException("El usuario no puede ser nulo");
        }

        Usuario usuarioExistente = usuarioRepository.findById(split.getUsuario().getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario con correo " + split.getUsuario().getCorreo() + " no encontrado"));

        // Asigna el usuario existente al Split
        split.setUsuario(usuarioExistente);

        return splitRepository.save(split);
    }

    public void deleteById(Integer id) {
        splitRepository.deleteById(id);
    }

    public List<Split> findByUsuarioCorreo(String correo) {
        return splitRepository.findByUsuarioCorreo(correo);
    }

    public void updateSplit(Integer id, String titulo, List<String> participantes) {
        Split split = splitRepository.findById(id).orElseThrow(() -> new RuntimeException("Split no encontrado"));

        split.setTitulo(titulo);
        split.setParticipantes(participantes);

        splitRepository.save(split);
    }
}

