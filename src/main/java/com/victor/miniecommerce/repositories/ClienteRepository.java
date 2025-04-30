package com.victor.miniecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.miniecommerce.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
