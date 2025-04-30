package com.victor.miniecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.miniecommerce.dto.ProdutoDTO;
import com.victor.miniecommerce.entities.Produto;
import com.victor.miniecommerce.repositories.ProdutoRepository;
import com.victor.miniecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoDTO> findAll() {
		List<Produto> list = produtoRepository.findAll();
		return list.stream().map(ProdutoDTO::new).toList();
	}

	public ProdutoDTO findById(Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProdutoDTO(produto);
	}

	public ProdutoDTO insert(Produto obj) {
		Produto saved = produtoRepository.save(obj);
		return new ProdutoDTO(saved);
	}

	public ProdutoDTO update(Long id, Produto obj) {
		Produto entity = buscarEntidadePorId(id);
		entity.setNome(obj.getNome());
		entity.setPreco(obj.getPreco());
		entity.setCategorias(obj.getCategorias());
		produtoRepository.save(entity);
		return new ProdutoDTO(entity);
	}

	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

	// Método auxiliar privado
	private Produto buscarEntidadePorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
