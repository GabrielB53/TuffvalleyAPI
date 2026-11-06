package com.itb.inf2cm.sgm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itb.inf2cm.sgm.model.entity.PratoProduto;
import com.itb.inf2cm.sgm.model.repository.PratoProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class PratoProdutoService {

    private final PratoProdutoRepository pratoProdutoRepository;

    public PratoProdutoService(PratoProdutoRepository pratoProdutoRepository) {
        this.pratoProdutoRepository = pratoProdutoRepository;
    }

    public PratoProduto findById(long id) {
        Optional<PratoProduto> pratoProduto = pratoProdutoRepository.findById(id);
        return pratoProduto.orElse(null);
    }

    public List<PratoProduto> findAll() {
        return pratoProdutoRepository.findAll();
    }
	public List<PratoProduto> findAllByStatus(String status) {
		List<PratoProduto> pratoProdutos = pratoProdutoRepository.findByStatusPratoProduto(status);
		return pratoProdutos;
	}


    @Transactional
    public PratoProduto create(PratoProduto pratoProduto) {
            pratoProduto.setStatusPratoProduto("ATIVO");
            return pratoProdutoRepository.save(pratoProduto);
    }

    @Transactional
    public PratoProduto editar(long id, PratoProduto pratoProduto) {
        Optional<PratoProduto> pratoProdutoExistente = pratoProdutoRepository.findById(id);

        if (pratoProdutoExistente.isPresent()) {
            PratoProduto pratoProdutoAtualizado = pratoProdutoExistente.get();
            pratoProdutoAtualizado.setStatusPratoProduto(pratoProduto.getStatusPratoProduto());

            return pratoProdutoRepository.save(pratoProdutoAtualizado);
        }
        return null;
    }

    @Transactional
    public PratoProduto inativar(long id) {
        Optional<PratoProduto> pratoProdutoExistente = pratoProdutoRepository.findById(id);

        if (pratoProdutoExistente.isPresent()) {
            PratoProduto pratoProdutoAtualizado = pratoProdutoExistente.get();
            pratoProdutoAtualizado.setStatusPratoProduto("INATIVO");
            return pratoProdutoRepository.save(pratoProdutoAtualizado);
        }
        return null;
    }
    
    @Transactional
    public void delete(long id) {
        pratoProdutoRepository.deleteById(id);
    }

    @Transactional
    public PratoProduto reativar(long id) {
        Optional<PratoProduto> pratoProdutoExistente = pratoProdutoRepository.findById(id);

        if (pratoProdutoExistente.isPresent()) {
            PratoProduto pratoProdutoAtualizado = pratoProdutoExistente.get();
            pratoProdutoAtualizado.setStatusPratoProduto("ATIVO");
            return pratoProdutoRepository.save(pratoProdutoAtualizado);
        }
        return null;
    }
}
