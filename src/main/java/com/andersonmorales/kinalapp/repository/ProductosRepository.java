package com.andersonmorales.kinalapp.repository;

import com.andersonmorales.kinalapp.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
}