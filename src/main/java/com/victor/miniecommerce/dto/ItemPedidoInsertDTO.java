package com.victor.miniecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoInsertDTO {
	
	Long pedidoId;
	Long produtoId;
	Integer quantidade;
	Double preco;
	
}
