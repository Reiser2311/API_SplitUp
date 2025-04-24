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

    public Optional<Usuario> findById(Integer id) { return usuarioRepository.findById(id); }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public void updateUsuario(Integer id, String correo, String nombre, String contrasenya) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setCorreo(correo);
        usuario.setNombre(nombre);
        usuario.setContrasenya(contrasenya);

        usuarioRepository.save(usuario);
    }
}
