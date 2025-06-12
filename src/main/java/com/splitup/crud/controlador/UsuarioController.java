package com.splitup.crud.controlador;

import com.splitup.crud.entidades.Usuario;
import com.splitup.crud.entidades.UsuarioDTO;
import com.splitup.crud.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService,  PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<UsuarioDTO> getUsuarioByCorreo(@PathVariable String correo) {
        Optional<Usuario> usuario = usuarioService.findByCorreo(correo);
        if (usuario.isPresent()) {
            Usuario u  = usuario.get();
            UsuarioDTO dto = new UsuarioDTO(u.getId(), u.getCorreo(), u.getNombre(), null);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            Usuario u  = usuario.get();
            UsuarioDTO dto = new UsuarioDTO(u.getId(), u.getCorreo(), u.getNombre(), u.getFotoPerfil());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody Usuario usuario) {
        Usuario creado = usuarioService.save(usuario);
        UsuarioDTO dto = new UsuarioDTO(creado.getId(), creado.getCorreo(), creado.getNombre(), null);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarioService.updateUsuario(id, usuario.getCorreo(), usuario.getNombre(), usuario.getContrasenya(), usuario.getFotoPerfil());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> optionalUsuario = usuarioService.findByCorreo(loginRequest.getCorreo());
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = optionalUsuario.get();
        if (!passwordEncoder.matches(loginRequest.getContrasenya(), usuario.getContrasenya())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsuarioDTO dto = new UsuarioDTO(usuario.getId(), usuario.getCorreo(), usuario.getNombre(), null);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}

