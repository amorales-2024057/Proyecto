package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.DetalleVenta;
import java.util.List;
import java.util.Optional;

public interface IDetalleVentasService {
    List<DetalleVenta> listarTodos();
    DetalleVenta guardar(DetalleVenta detalleVenta);
    Optional<DetalleVenta> buscarPorCodigo(long codigo);
    DetalleVenta actualizar(long codigo, DetalleVenta detalleVenta);
    boolean existePorCodigo(long codigo);
}