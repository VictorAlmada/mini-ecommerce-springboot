package com.victor.miniecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.miniecommerce.dto.PedidoDTO;
import com.victor.miniecommerce.entities.Pedido;
import com.victor.miniecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		return ResponseEntity.ok(pedidoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
		PedidoDTO dto = pedidoService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<PedidoDTO> insert(@RequestBody Pedido obj) {
		PedidoDTO dto = pedidoService.insert(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pedidoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
