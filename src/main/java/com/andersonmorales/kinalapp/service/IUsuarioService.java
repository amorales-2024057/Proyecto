package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarTodos();
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorCodigo(long codigo);
    Optional<Usuario> buscarPorEstado(int estado);
    Usuario actualizar(long codigo, Usuario usuario);
    void eliminar(long codigo);
    boolean existePorCodigo(long codigo);
}