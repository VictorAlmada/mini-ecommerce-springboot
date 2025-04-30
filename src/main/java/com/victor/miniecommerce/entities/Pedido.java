package com.victor.miniecommerce.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pedido")
@NoArgsConstructor
@Getter @Setter
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Instant data;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	Cliente cliente;
	
	@OneToMany(mappedBy = "id.pedido")
	@JsonManagedReference
	@JsonIgnore
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido(Long id, Instant data, Cliente cliente) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
	}
	
	public Double getTotal() {
		double soma = 0.0;
		for (ItemPedido item : itens) {
			soma += item.getSubTotal();
		}
		return soma;
	}
	
	public Pedido(Long id) {
	    this.id = id;
	}

	
	
}
