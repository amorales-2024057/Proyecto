package com.andersonmorales.kinalapp.repository;

import com.andersonmorales.kinalapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<Ventas, Long> {
}