package com.victor.miniecommerce.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.victor.miniecommerce.entities.Pedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {
	
	private Long id;
	private Instant data;
	private ClienteDTO cliente;
	private Double total;
	private List<ItemPedidoDTO> itens;
	
	public PedidoDTO(Pedido entity) {
		this.id = entity.getId();
		this.data = entity.getData();
		this.total = entity.getTotal();
		
		if (entity.getCliente() != null) {
			this.cliente = new ClienteDTO(entity.getCliente());
		}
		
		this.itens = entity.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
	}
	
	
}
