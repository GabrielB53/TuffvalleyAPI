package com.itb.inf2cm.sgm.rest.mapper;

import com.itb.inf2cm.sgm.model.entity.Categoria;
import com.itb.inf2cm.sgm.rest.dto.CategoriaDTO;

public class CategoriaMapper {
    public static CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setStatusCategoria(categoria.getStatusCategoria());

        return dto;
    }
}
