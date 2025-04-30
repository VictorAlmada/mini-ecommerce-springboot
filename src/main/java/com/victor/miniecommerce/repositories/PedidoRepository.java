package com.victor.miniecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.miniecommerce.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
