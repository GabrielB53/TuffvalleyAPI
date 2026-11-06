package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itb.inf2cm.sgm.model.entity.Prato;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {
	Prato findAllById(long id);

	List<Prato> findByNomeContainingIgnoreCase(String nome);

	List<Prato> findByStatusPrato(String status);
}
