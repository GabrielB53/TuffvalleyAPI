package com.itb.inf2cm.sgm.rest.mapper;
 
import com.itb.inf2cm.sgm.model.entity.Prato;
import com.itb.inf2cm.sgm.rest.dto.PratoDTO;
 
import org.springframework.stereotype.Component;
 
 
@Component
public class PratoMapper {
 
    public static PratoDTO toDTO(Prato prato) {
        PratoDTO dto = new PratoDTO();
        dto.setId(prato.getId());
        dto.setNome(prato.getNome());
        dto.setDescricao(prato.getDescricao());
        dto.setPrincipal(prato.getPrincipal());
        dto.setSecundario(prato.getSecundario());
        dto.setAcompanhamento(prato.getAcompanhamento());
        dto.setStatusPrato(prato.getStatusPrato());
 
        return dto;
    }
}