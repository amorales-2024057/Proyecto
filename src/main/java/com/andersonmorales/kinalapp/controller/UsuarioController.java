package com.andersonmorales.kinalapp.controller;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {

        List<Usuario> usuarios = usuarioService.listarTodos();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable long codigo) {

        return usuarioService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Usuario> buscarPorEstado(@PathVariable int estado) {

        return usuarioService.buscarPorEstado(estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {

        try {

            Usuario nuevoUsuario = usuarioService.guardar(usuario);

            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable long codigo) {

        try {

            if (!usuarioService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }

            usuarioService.eliminar(codigo);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable long codigo, @RequestBody Usuario usuario) {

        try {

            if (!usuarioService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }

            Usuario usuarioActualizado = usuarioService.actualizar(codigo, usuario);

            return ResponseEntity.ok(usuarioActualizado);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}