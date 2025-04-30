package com.victor.miniecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.victor.miniecommerce.dto.ClienteDTO;
import com.victor.miniecommerce.entities.Cliente;
import com.victor.miniecommerce.repositories.ClienteRepository;
import com.victor.miniecommerce.services.exceptions.DatabaseException;
import com.victor.miniecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<ClienteDTO> findAll() {
		return clienteRepository.findAll().stream().map(ClienteDTO::new).toList();
	}
	
	public ClienteDTO findById(Long id) {
		Cliente entity = buscarEntidadePorId(id);
		return new ClienteDTO(entity);
	}
	
	public ClienteDTO insert(Cliente obj) {
		Cliente saved = clienteRepository.save(obj);
		return new ClienteDTO(saved);
	}
	
	public ClienteDTO update(Long id, Cliente obj) {
		Cliente entity = buscarEntidadePorId(id);
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		clienteRepository.save(entity);
		return new ClienteDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade referencial");
		}
	}
	
	private Cliente buscarEntidadePorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
