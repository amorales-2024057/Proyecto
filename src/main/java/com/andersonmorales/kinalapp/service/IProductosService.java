package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Productos;
import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos> listarTodos();
    Productos guardar(Productos producto);
    Optional<Productos> buscarPorCodigo(Long codigo);  // Cambiado a Long
    Optional<Productos> buscarPorEstado(int estado);
    Productos actualizar(Long codigo, Productos producto);  // Cambiado a Long
    void eliminar(Long codigo);  // Cambiado a Long
    boolean existePorCodigo(Long codigo);  // Cambiado a Long
}