package com.victor.miniecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victor.miniecommerce.dto.ItemPedidoDTO;
import com.victor.miniecommerce.dto.ItemPedidoInsertDTO;
import com.victor.miniecommerce.services.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	public ResponseEntity<List<ItemPedidoDTO>> findAll() {
		List<ItemPedidoDTO> list = itemPedidoService.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<ItemPedidoDTO> findById(@RequestParam Long pedidoId, @RequestParam Long produtoId) {
		ItemPedidoDTO dto = itemPedidoService.findById(pedidoId, produtoId);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<ItemPedidoDTO> insert(@RequestBody ItemPedidoInsertDTO dto) {
		ItemPedidoDTO newItem = itemPedidoService.insert(dto);
		return ResponseEntity.ok(newItem);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<ItemPedidoDTO> update(@RequestParam Long pedidoId, @RequestParam Long produtoId, @RequestBody ItemPedidoInsertDTO dto) {
		ItemPedidoDTO updated = itemPedidoService.update(pedidoId, produtoId, dto);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity<Void> delete(@RequestParam Long pedidoId, @RequestParam Long produtoId) {
		itemPedidoService.delete(pedidoId, produtoId);
		return ResponseEntity.noContent().build();
	}
}
