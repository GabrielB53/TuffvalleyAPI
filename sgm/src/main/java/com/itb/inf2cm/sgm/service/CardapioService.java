package com.itb.inf2cm.sgm.service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itb.inf2cm.sgm.model.entity.Cardapio;

import com.itb.inf2cm.sgm.model.repository.CardapioRepository;
import jakarta.transaction.Transactional;

@Service
public class CardapioService {

	private CardapioRepository cardapioRepository;

	public CardapioService(CardapioRepository cardapioRepository) {
		super();
		this.cardapioRepository = cardapioRepository;
	}

	public Cardapio findById(long id) {

		Optional<Cardapio> cardapio = cardapioRepository.findById(id);

		if (cardapio.isPresent()) {
			return cardapio.get();
		}

		return null;
	}

	public List<Cardapio> findAll() {
		List<Cardapio> cardapios = cardapioRepository.findAll();
		return cardapios;
	}
	
	public List<Cardapio> findAllByStatus(String status) {
		List<Cardapio> cardapios = cardapioRepository.findByStatusCardapio(status);
		return cardapios;
	}
	public List<Cardapio> findByNomeContainingIgnoreCase(String nome) {
	    return cardapioRepository.findByNomeContainingIgnoreCase(nome);
	}

	@Transactional
	public Cardapio create(Cardapio cardapio) {
	    if (cardapio.getStatusCardapio() == null || cardapio.getStatusCardapio().isEmpty()) {
	        cardapio.setStatusCardapio("ATIVO");
	    }
	    return cardapioRepository.save(cardapio);
	}

	@Transactional
	public Cardapio editar(MultipartFile file, long id, Cardapio cardapio) {
	    Optional<Cardapio> _cardapio = cardapioRepository.findById(id);

	    if (_cardapio.isPresent()) {
	        Cardapio cardapioAtualizado = _cardapio.get();

	        cardapioAtualizado.setNome(cardapio.getNome());
	        cardapioAtualizado.setStatusCardapio(cardapio.getStatusCardapio());

	        if (file != null && file.getSize() > 0) {
	            try {
	                cardapioAtualizado.setFoto(file.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } 

	        return cardapioRepository.save(cardapioAtualizado);
	    }
	    return null;
	}

	
	@Transactional
	public Cardapio inativar(long id) {
		Optional<Cardapio> _cardapio = cardapioRepository.findById(id);

		if (_cardapio.isPresent()) {
			Cardapio cardapioAtualizado = _cardapio.get();

			cardapioAtualizado.setStatusCardapio("INATIVO");

			return cardapioRepository.save(cardapioAtualizado);
		}
		return null;
	}

	@Transactional
	public Cardapio reativar(long id) {
		Optional<Cardapio> _cardapio = cardapioRepository.findById(id);

		if (_cardapio.isPresent()) {
			Cardapio cardapioAtualizado = _cardapio.get();

			cardapioAtualizado.setStatusCardapio("ATIVO");

			return cardapioRepository.save(cardapioAtualizado);
		}
		return null;
	}





}
