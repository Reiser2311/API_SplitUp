package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{correo}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String correo) {
        Optional<Usuario> usuario = usuarioService.findByCorreo(correo);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{correo}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String correo, @RequestBody Usuario usuario) {
        if (usuarioService.findByCorreo(correo).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        usuario.setCorreo(correo);
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @DeleteMapping("/{correo}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String correo) {
        usuarioService.deleteById(correo);
        return ResponseEntity.noContent().build();
    }
}

