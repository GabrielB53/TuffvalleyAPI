package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.itb.inf2cm.sgm.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	List<Usuario> findByStatusUsuario(String string);

	List<Usuario> findByNomeContainingIgnoreCase(String nome);

}
