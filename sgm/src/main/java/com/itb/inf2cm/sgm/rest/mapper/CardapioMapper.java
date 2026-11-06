package com.itb.inf2cm.sgm.rest.mapper;

import com.itb.inf2cm.sgm.model.entity.Cardapio;
import com.itb.inf2cm.sgm.rest.dto.CardapioDTO;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class CardapioMapper {

	public static CardapioDTO toDTO(Cardapio cardapio) {
	    CardapioDTO dto = new CardapioDTO();
	    dto.setId(cardapio.getId());
	    dto.setNome(cardapio.getNome());
	    
	    if (cardapio.getPrato() != null) {
	        dto.setPratoId(cardapio.getPrato().getId());  
	    }
	    
	    dto.setDiaServido(cardapio.getDiaServido());
	    dto.setStatusCardapio(cardapio.getStatusCardapio());

	    if (cardapio.getFoto() != null) {
	        String base64 = Base64.getEncoder().encodeToString(cardapio.getFoto());
	        dto.setFoto("data:image/jpeg;base64," + base64);
	    }

	    return dto;
	}

}
