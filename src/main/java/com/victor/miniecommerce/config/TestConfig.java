package com.victor.miniecommerce.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victor.miniecommerce.entities.Categoria;
import com.victor.miniecommerce.entities.Cliente;
import com.victor.miniecommerce.entities.ItemPedido;
import com.victor.miniecommerce.entities.Pedido;
import com.victor.miniecommerce.entities.Produto;
import com.victor.miniecommerce.repositories.CategoriaRepository;
import com.victor.miniecommerce.repositories.ClienteRepository;
import com.victor.miniecommerce.repositories.ItemPedidoRepository;
import com.victor.miniecommerce.repositories.PedidoRepository;
import com.victor.miniecommerce.repositories.ProdutoRepository;

@Configuration
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Override
	public void run(String... args) throws Exception {
		
		//CATEGORIAS
		Categoria cat1 = new Categoria(null, "Eletrônicos");
		Categoria cat2 = new Categoria(null, "Livros");
		Categoria cat3 = new Categoria(null, "Roupas");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		//PRODUTOS
		Produto p1 = new Produto(null, "Smartphone", 2000.00);
		Produto p2 = new Produto(null, "Livro de Java", 100.00);
		Produto p3 = new Produto(null, "Camiseta", 80.00);
		
		//ASSOCIAÇÕES PRODUTO-CATEGORIA
		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat2);
		p3.getCategorias().add(cat3);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//CLIENTES	
		Cliente c1 = new Cliente(null, "Victor Costa", "victor@email.com");
		Cliente c2 = new Cliente(null, "Ana Silva", "ana@email.com");
		
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		
		//PEDIDOS
		Pedido ped1 = new Pedido(null, Instant.parse("2025-04-22T10:15:00Z"), c1);
		Pedido ped2 = new Pedido(null, Instant.parse("2025-04-22T12:30:00Z"), c2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		//ITENS DO PEDIDO
		ItemPedido ip1 = new ItemPedido(ped1, p1, 1, p1.getPreco()); // 1x Smartphone
		ItemPedido ip2 = new ItemPedido(ped1, p2, 2, p2.getPreco()); // 2x Livro
		ItemPedido ip3 = new ItemPedido(ped2, p3, 1, p3.getPreco()); // 3x Camiseta
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	
	}
	
