package com.itb.inf2cm.sgm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itb.inf2cm.sgm.model.entity.Produto;
import com.itb.inf2cm.sgm.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	/* INSTANCIA O OBJETO PARA ACESSAR OS RECURSOS DA CLASSE / INTERFACE */
	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	
	public List<Produto> findByNomeContainingIgnoreCase(String nome) {
	    return produtoRepository.findByNomeContainingIgnoreCase(nome);
	}
	/* LISTA TODOS OS REGISTROS DA TABELA */
	public List<Produto> findAll(){
		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}
	
	/* LISTA TODOS OS REGISTROS DE ACORDO COM FILTRO INDICADO */
	public List<Produto> findAllByStatus(String statusProduto) {
		List<Produto> produtos = produtoRepository.findByStatusProduto(statusProduto);
		return produtos;
	}
	
	/* BUSCA UM REGISTRO NA TABELA FILTRANDO PELO "id" */
	public Produto findById(long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return produto.get();
		}
		return null;
	}
	
	
	/* SALVA O REGISTRO NA BASE DE DADOS */
	@Transactional
	public Produto create(Produto produto) {
		/* DADOS QUE SERÃO ARMAZENADOS POR PADRÃO NA BASE DE DADOS */
		/* ESSES DADOS NÃO DEVEM SER PASSADOS NO FRONTEND */
		produto.setStatusProduto("ATIVO");
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public Produto editar(long id, Produto produto) {
		Optional<Produto> _produto = produtoRepository.findById(id);
		if (_produto.isPresent()) {
			Produto produtoAtualizado = _produto.get();
			
			produtoAtualizado.setCategoria(produto.getCategoria());
			produtoAtualizado.setNome(produto.getNome());
			produtoAtualizado.setDescricao(produto.getDescricao());
			produtoAtualizado.setInfoNutricional(produto.getInfoNutricional());
			produtoAtualizado.setPorcao(produto.getPorcao());
			
			return produtoRepository.save(produtoAtualizado);
		}
		return null;
	}
	
	//Ativar e reativar o produto
	@Transactional
	public Produto inativar(long id) {
		Optional<Produto> _produto = 
				produtoRepository.findById(id);
		
		if (_produto.isPresent()) {
			Produto produtoAtualizada = _produto.get();
			produtoAtualizada.setStatusProduto("INATIVO");
			
			return produtoRepository.save(produtoAtualizada);
		}
		return null;
	}
	@Transactional
	public Produto reativar(long id) {
		Optional<Produto> _produto = 
				produtoRepository.findById(id);
		
		if (_produto.isPresent()) {
			Produto produtoAtualizado = _produto.get();
			produtoAtualizado.setStatusProduto("ATIVO");
			
			return produtoRepository.save(produtoAtualizado);
		}
		return null;
	}
	

}
