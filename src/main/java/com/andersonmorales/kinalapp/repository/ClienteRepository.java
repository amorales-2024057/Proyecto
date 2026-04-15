package com.andersonmorales.kinalapp.repository;

import com.andersonmorales.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}