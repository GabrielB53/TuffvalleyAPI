package com.itb.inf2cm.sgm.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.itb.inf2cm.sgm.model.entity.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

	/* LISTA TODOS OS REGISTROS DE ACORDO COM FILTRO INDICADO */
	/* APÓS O COMANDO "findBy[campo]" DEVE SER INFORMADO UM CAMPO VÁLIDO */
	/* O CAMPO VÁLIDO DEVE SER UM ATRIBUTO DA CLASSE DE ENTIDADE */
	List<Mensagem> findByEmail(String email);

	List<Mensagem> findByStatusMensagem(String statusMensagem);

    List<Mensagem> findByEmailContainingIgnoreCase(String email);
}
