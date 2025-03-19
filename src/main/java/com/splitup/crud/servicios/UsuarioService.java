package com.splitup.crud.servicios;

import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findByCorreo(String correo) { return usuarioRepository.findById(correo); }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(String correo) {
        usuarioRepository.deleteById(correo);
    }

    public void updateUsuario(String correo, String nombre, String contrasenya) {
        Usuario usuario = usuarioRepository.findById(correo).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(nombre);
        usuario.setContrasenya(contrasenya);

        usuarioRepository.save(usuario);
    }
}
