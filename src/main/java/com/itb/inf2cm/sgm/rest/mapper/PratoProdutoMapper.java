package com.itb.inf2cm.sgm.rest.mapper;

import com.itb.inf2cm.sgm.model.entity.PratoProduto;
import com.itb.inf2cm.sgm.rest.dto.PratoProdutoDTO;

public class PratoProdutoMapper {
	public static PratoProdutoDTO toDTO(PratoProduto pratoProduto) {
	    PratoProdutoDTO dto = new PratoProdutoDTO();
	    dto.setId(pratoProduto.getId());
	    dto.setInformacao(pratoProduto.getInformacao());
	    
	    if (pratoProduto.getPrato() != null) {
	        dto.setPratoId(pratoProduto.getPrato().getId());  
	    }
	    if (pratoProduto.getPrato() != null) {
	        dto.setProdutoId(pratoProduto.getProduto().getId());  
	    }

	    dto.setStatusPratoProduto(pratoProduto.getStatusPratoProduto());

	    return dto;
	}
}
