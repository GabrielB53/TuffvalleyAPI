package com.itb.inf2cm.sgm.rest.mapper;

import com.itb.inf2cm.sgm.model.entity.Produto;
import com.itb.inf2cm.sgm.rest.dto.ProdutoDTO;

public class ProdutoMapper {
	public static ProdutoDTO toDTO(Produto produto) {
	    ProdutoDTO dto = new ProdutoDTO();
	    dto.setId(produto.getId());
	    dto.setNome(produto.getNome());
	    
	    if (produto.getCategoria() != null) {
	        dto.setCategoriaId(produto.getCategoria().getId());  
	    }
	    dto.setDescricao(produto.getDescricao());
	    dto.setInfoNutricional(produto.getInfoNutricional());
	    dto.setPorcao(produto.getPorcao());
	    dto.setStatusProduto(produto.getStatusProduto());

	    return dto;
	}

}
