package com.victor.miniecommerce.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	
	@ManyToMany(mappedBy = "categorias")
	@Setter(lombok.AccessLevel.NONE)
	private Set<Produto> produtos = new HashSet<>();
	
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
}
