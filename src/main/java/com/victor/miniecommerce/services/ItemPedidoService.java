package com.victor.miniecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.miniecommerce.dto.ItemPedidoDTO;
import com.victor.miniecommerce.dto.ItemPedidoInsertDTO;
import com.victor.miniecommerce.entities.ItemPedido;
import com.victor.miniecommerce.entities.Pedido;
import com.victor.miniecommerce.entities.Produto;
import com.victor.miniecommerce.entities.pk.ItemPedidoPK;
import com.victor.miniecommerce.repositories.ItemPedidoRepository;
import com.victor.miniecommerce.repositories.PedidoRepository;
import com.victor.miniecommerce.repositories.ProdutoRepository;
import com.victor.miniecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	// LISTAR TODOS
	public List<ItemPedidoDTO> findAll() {
		return itemPedidoRepository.findAll().stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
	}

	// PROCURAR POR ID
	public ItemPedidoDTO findById(Long pedidoId, Long produtoId) {
		ItemPedidoPK id = new ItemPedidoPK();

		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new ResourceNotFoundException(pedidoId));
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException(pedidoId));
		id.setPedido(pedido);
		id.setProduto(produto);

		Optional<ItemPedido> obj = itemPedidoRepository.findById(id);
		ItemPedido entity = obj.orElseThrow(() -> new ResourceNotFoundException("ItemPedido"));
		return new ItemPedidoDTO(entity);

	}

	// INSERIR
	public ItemPedidoDTO insert(ItemPedidoInsertDTO dto) {
		Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
				.orElseThrow(() -> new ResourceNotFoundException(dto.getPedidoId()));
		Produto produto = produtoRepository.findById(dto.getProdutoId())
				.orElseThrow(() -> new ResourceNotFoundException(dto.getProdutoId()));

		ItemPedido item = new ItemPedido(pedido, produto, dto.getQuantidade(), dto.getPreco());

		item = itemPedidoRepository.save(item);
		return new ItemPedidoDTO(item);
	}

	// ATUALIZAR
	public ItemPedidoDTO update(Long pedidoId, Long produtoId, ItemPedidoInsertDTO dto) {
		ItemPedidoPK pk = new ItemPedidoPK();

		Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() -> new ResourceNotFoundException(pedidoId));
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException(produtoId));
		pk.setPedido(pedido);
		pk.setProduto(produto);

		ItemPedido entity = itemPedidoRepository.findById(pk)
				.orElseThrow(() -> new ResourceNotFoundException("ItemPedido não encontrado"));

		entity.setQuantidade(dto.getQuantidade());
		entity.setPreco(dto.getPreco());

		entity = itemPedidoRepository.save(entity);
		return new ItemPedidoDTO(entity);
	}

	// DELETAR
	public void delete(Long pedidoId, Long produtoId) {
		ItemPedidoPK pk = new ItemPedidoPK();
		Pedido pedido = new Pedido();
		pedido.setId(pedidoId);
		Produto produto = new Produto();
		produto.setId(produtoId);
		pk.setPedido(pedido);
		pk.setProduto(produto);

		if (!itemPedidoRepository.existsById(pk)) {
			throw new ResourceNotFoundException("ItemPedido não encontrado");
		}

		itemPedidoRepository.deleteById(pk);
	}

}
