package com.andersonmorales.kinalapp.repository;

import com.andersonmorales.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente,String>{
}

