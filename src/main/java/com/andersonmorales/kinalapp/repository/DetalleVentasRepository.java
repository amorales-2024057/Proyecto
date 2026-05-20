package com.andersonmorales.kinalapp.repository;

import com.andersonmorales.kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentasRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByProductosCodigoProducto(Long codigoProducto);

    List<DetalleVenta> findByVentasCodigoVenta(Long codigoVenta);
}