package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Split;
import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.repositorio.SplitRepository;
import com.splitup.crud.repositorio.UsuarioRepository;
import com.splitup.crud.repositorio.UsuarioSplitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final SplitRepository splitRepository;
    private final UsuarioSplitRepository usuarioSplitRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioSplitRepository usuarioSplitRepository,
                          SplitRepository splitRepository,  PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.splitRepository = splitRepository;
        this.usuarioSplitRepository = usuarioSplitRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Integer id) { return usuarioRepository.findById(id); }

    public Optional<Usuario> findByCorreo(String correo) { return usuarioRepository.findByCorreo(correo); }

    public Usuario save(Usuario usuario) {
        String rawContrasenya = usuario.getContrasenya();
        String hashedContrasenya = passwordEncoder.encode(rawContrasenya);
        usuario.setContrasenya(hashedContrasenya);
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);

        List<Split> todosLosSplits = splitRepository.findAll();

        for (Split split : todosLosSplits) {
            boolean sigueRelacionado = usuarioSplitRepository.existsBySplitId(split.getId());
            if (!sigueRelacionado) {
                splitRepository.deleteById(split.getId());
            }
        }
    }

    public void updateUsuario(Integer id, String correo, String nombre, String contrasenya, String fotoPerfil) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        usuario.setContrasenya(contrasenya);
        usuario.setFotoPerfil(fotoPerfil);

        save(usuario);
    }
}
