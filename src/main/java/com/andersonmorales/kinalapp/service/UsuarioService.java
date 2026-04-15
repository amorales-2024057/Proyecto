package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        validarUsuario(usuario);

        if (usuario.getEstado() == 0) {
            usuario.setEstado(1);
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCodigo(long codigo) {
        return usuarioRepository.findById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEstado(int estado) {
        return usuarioRepository.findAll()
                .stream()
                .filter(usuario -> usuario.getEstado() == estado)
                .findAny();
    }

    @Override
    public Usuario actualizar(long codigo, Usuario usuario) {

        if (!usuarioRepository.existsById(codigo)) {
            throw new RuntimeException("Usuario no encontrado con código " + codigo);
        }

        usuario.setCodigoUsuario(codigo);

        validarUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(long codigo) {

        if (!usuarioRepository.existsById(codigo)) {
            throw new RuntimeException("Usuario no encontrado con código " + codigo);
        }

        usuarioRepository.deleteById(codigo);
    }

    @Override
    public boolean existePorCodigo(long codigo) {
        return usuarioRepository.existsById(codigo);
    }

    private void validarUsuario(Usuario usuario) {

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El username es obligatorio");
        }

        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("El password es obligatorio");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }

    }
}