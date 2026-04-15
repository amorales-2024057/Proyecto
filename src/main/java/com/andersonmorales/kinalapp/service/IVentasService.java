package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Ventas;
import java.util.List;
import java.util.Optional;

public interface IVentasService {
    List<Ventas> listarTodos();
    Ventas guardar(Ventas ventas);
    Optional<Ventas> buscarporCodigo(long codigoVentas);
    Optional<Ventas> buscarporEstado(int estado);
    Ventas actualizar(long codigoVentas, Ventas ventas);
    boolean existeporCodigo(long codigoVentas);
}