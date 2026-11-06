package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itb.inf2cm.sgm.model.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByStatusProduto(String statusProduto);

	List<Produto> findByNomeContainingIgnoreCase(String nome);

}
