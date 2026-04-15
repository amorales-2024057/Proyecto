package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos> listarTodos();
    Productos guardar(Productos producto);
    Optional<Productos> buscarPorCodigo(long codigo);
    Optional<Productos> buscarPorEstado(int estado);
    Productos actualizar(long codigo, Productos producto);
    void eliminar(long codigo);
    boolean existePorCodigo(long codigo);
}