package com.itb.inf2cm.sgm.rest.mapper;

import com.itb.inf2cm.sgm.model.entity.Usuario;
import com.itb.inf2cm.sgm.rest.dto.UsuarioDTO;

import java.util.Base64;

public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setNivelAcesso(usuario.getNivelAcesso());
        dto.setDataCadastro(usuario.getDataCadastro());
        dto.setStatusUsuario(usuario.getStatusUsuario());

        if (usuario.getFoto() != null) {
            String base64 = Base64.getEncoder().encodeToString(usuario.getFoto());
            dto.setFoto("data:image/jpeg;base64," + base64);
        }

        return dto;
    }
}