package com.victor.miniecommerce.dto;

import com.victor.miniecommerce.entities.ItemPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoDTO {

	private Long produtoId;
	private String nomeProduto;
	private Integer quantidade;
	private Double preco;
	private Double subTotal;
	
	public ItemPedidoDTO(ItemPedido entity) {
		this.produtoId = entity.getProduto().getId();
		this.nomeProduto = entity.getProduto().getNome();
		this.quantidade = entity.getQuantidade();
		this.preco = entity.getPreco();
		this.subTotal = entity.getSubTotal();
		
	}

}
