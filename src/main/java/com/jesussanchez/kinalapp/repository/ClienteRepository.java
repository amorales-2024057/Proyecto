package com.jesussanchez.kinalapp.repository;

import com.jesussanchez.kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente,String>{
}
