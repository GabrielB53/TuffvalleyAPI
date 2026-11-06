package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itb.inf2cm.sgm.model.entity.PratoProduto;

@Repository
public interface PratoProdutoRepository extends JpaRepository<PratoProduto, Long> {
	PratoProduto findAllById(long id);

	List<PratoProduto> findByStatusPratoProduto(String status);
}
