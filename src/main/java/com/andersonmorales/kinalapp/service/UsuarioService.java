package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.entity.Ventas;
import com.andersonmorales.kinalapp.repository.DetalleVentasRepository;
import com.andersonmorales.kinalapp.repository.UsuarioRepository;
import com.andersonmorales.kinalapp.repository.VentasRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final VentasRepository ventasRepository;
    private final DetalleVentasRepository detalleVentasRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          VentasRepository ventasRepository,
                          DetalleVentasRepository detalleVentasRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.ventasRepository = ventasRepository;
        this.detalleVentasRepository = detalleVentasRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        validarUsuario(usuario);
        if (usuario.getPassword() != null && !usuario.getPassword().trim().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        if (usuario.getEstado() == null || usuario.getEstado() == 0) {
            usuario.setEstado(1);
        }
        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDateTime.now());
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
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario login(String username, String password) {
        Optional<Usuario> opt = usuarioRepository.findByUsername(username);
        if (opt.isPresent()) {
            Usuario usuario = opt.get();
            if (usuario.getPassword().equals(password) && usuario.getEstado() == 1) {
                usuario.setUltimoLogin(LocalDateTime.now());
                usuarioRepository.save(usuario);
                return usuario;
            }
        }
        return null;
    }

    @Override
    public boolean registrar(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            return false;
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return false;
        }
        usuario.setRol("USER");
        usuario.setEstado(1);
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEstado(int estado) {
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEstado() == estado)
                .findAny();
    }

    @Override
    public Usuario actualizar(long codigo, Usuario usuario) {
        if (!usuarioRepository.existsById(codigo)) {
            throw new RuntimeException("Usuario no encontrado con código " + codigo);
        }
        Usuario existente = usuarioRepository.findById(codigo).get();
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            usuario.setPassword(existente.getPassword());
        } else {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
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
        List<Ventas> ventas = ventasRepository.findByUsuarioCodigoUsuario(codigo);
        for (Ventas venta : ventas) {
            detalleVentasRepository.deleteAll(
                    detalleVentasRepository.findByVentasCodigoVenta(venta.getCodigoVenta())
            );
        }
        ventasRepository.deleteAll(ventas);
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
        if (usuario.getNombreCompleto() == null || usuario.getNombreCompleto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
    }
}