package com.victor.miniecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.miniecommerce.dto.CategoriaDTO;
import com.victor.miniecommerce.entities.Categoria;
import com.victor.miniecommerce.repositories.CategoriaRepository;
import com.victor.miniecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<CategoriaDTO> findAll() {
		List<Categoria> list = categoriaRepository.findAll();
		return list.stream().map(CategoriaDTO::new).toList();		
	}
	
	public CategoriaDTO findById(Long id) {
		Categoria entity = buscarEntidadePorId(id);
		return new CategoriaDTO(entity);
	}
	
	public CategoriaDTO insert(Categoria obj) {
		Categoria saved = categoriaRepository.save(obj);
		return new CategoriaDTO(saved);
	}
	
	public CategoriaDTO update(Long id, Categoria obj) {
		Categoria entity = buscarEntidadePorId(id);
		entity.setNome(obj.getNome());
		categoriaRepository.save(entity);
		return new CategoriaDTO(entity);
	}
	
	public void delete(Long id) {
		categoriaRepository.deleteById(id);
	}
	
	// Método auxiliar privado
	private Categoria buscarEntidadePorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
}
