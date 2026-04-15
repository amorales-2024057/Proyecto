package com.andersonmorales.kinalapp.repository;

import com.andersonmorales.kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentasRepository extends JpaRepository<DetalleVenta, Long> {
}