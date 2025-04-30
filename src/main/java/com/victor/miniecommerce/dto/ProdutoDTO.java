package com.victor.miniecommerce.dto;

import com.victor.miniecommerce.entities.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private Double preco;
	
	public ProdutoDTO(Produto entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.preco = entity.getPreco();
	}
	
}
