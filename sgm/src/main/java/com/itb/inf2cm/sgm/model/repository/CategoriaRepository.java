package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.itb.inf2cm.sgm.model.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	Categoria findAllById(long id);

	List<Categoria> findByNomeContainingIgnoreCase(String nome);

	List<Categoria> findByStatusCategoria(String status);
}
