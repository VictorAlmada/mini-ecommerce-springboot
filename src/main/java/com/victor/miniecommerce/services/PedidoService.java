package com.victor.miniecommerce.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.victor.miniecommerce.dto.PedidoDTO;
import com.victor.miniecommerce.entities.Pedido;
import com.victor.miniecommerce.repositories.PedidoRepository;
import com.victor.miniecommerce.services.exceptions.DatabaseException;
import com.victor.miniecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<PedidoDTO> findAll() {
		return pedidoRepository.findAll().stream().map(PedidoDTO::new).toList();
	}
	
	public PedidoDTO findById(Long id) {
		Pedido entity = buscarEntidadePorId(id);
		return new PedidoDTO(entity);
	}
	
	public PedidoDTO insert(Pedido obj) {
		obj.setData(Instant.now());
		Pedido saved = pedidoRepository.save(obj);
		return new PedidoDTO(saved);
	}
	
	public void delete(Long id) {
		try {
			pedidoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade referencial");
		}
	}
	
	private Pedido buscarEntidadePorId(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
}
