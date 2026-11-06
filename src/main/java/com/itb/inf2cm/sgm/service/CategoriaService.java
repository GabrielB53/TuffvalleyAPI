package com.itb.inf2cm.sgm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itb.inf2cm.sgm.model.entity.Categoria;
import com.itb.inf2cm.sgm.model.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
	public List<Categoria> findAllByStatus(String status) {
		List<Categoria> categorias = categoriaRepository.findByStatusCategoria(status);
		return categorias;
	}

    public List<Categoria> findByNomeContainingIgnoreCase(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Categoria create(Categoria categoria) {
        if (categoria != null && categoria.getNome() != null && !categoria.getNome().isEmpty()) {
            categoria.setStatusCategoria("ATIVO");
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    @Transactional
    public Categoria editar(long id, Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoriaAtualizado = categoriaExistente.get();
            categoriaAtualizado.setNome(categoria.getNome());
            return categoriaRepository.save(categoriaAtualizado);
        }
        return null;
    }

    @Transactional
    public Categoria inativar(long id) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoriaAtualizado = categoriaExistente.get();
            categoriaAtualizado.setStatusCategoria("INATIVO");
            return categoriaRepository.save(categoriaAtualizado);
        }
        return null;
    }
    
    @Transactional
    public void delete(long id) {
        categoriaRepository.deleteById(id);
    }

    @Transactional
    public Categoria reativar(long id) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {
            Categoria categoriaAtualizado = categoriaExistente.get();
            categoriaAtualizado.setStatusCategoria("ATIVO");
            return categoriaRepository.save(categoriaAtualizado);
        }
        return null;
    }
}
