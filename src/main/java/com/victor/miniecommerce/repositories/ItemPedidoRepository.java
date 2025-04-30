package com.victor.miniecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.miniecommerce.entities.ItemPedido;
import com.victor.miniecommerce.entities.pk.ItemPedidoPK;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

	ItemPedido findByPedidoIdAndProdutoId(Long pedidoId, Long produtoId);

}
