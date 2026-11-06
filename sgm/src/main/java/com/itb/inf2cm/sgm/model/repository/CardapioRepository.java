package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itb.inf2cm.sgm.model.entity.Cardapio;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
	Cardapio findAllById(long id);

	List<Cardapio> findByStatusCardapio(String statusCardapio);

	List<Cardapio> findByNomeContainingIgnoreCase(String nome);
}
