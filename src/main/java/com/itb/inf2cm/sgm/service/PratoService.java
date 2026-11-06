package com.itb.inf2cm.sgm.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
 
import com.itb.inf2cm.sgm.model.entity.Prato;
import com.itb.inf2cm.sgm.model.repository.PratoRepository;
import jakarta.transaction.Transactional;
 
@Service
public class PratoService {
 
    private final PratoRepository pratoRepository;
 
    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }
 
    public Prato findById(long id) {
        Optional<Prato> prato = pratoRepository.findById(id);
        return prato.orElse(null);
    }
 
    public List<Prato> findAll() {
        return pratoRepository.findAll();
    }
	public List<Prato> findAllByStatus(String status) {
		List<Prato> pratos = pratoRepository.findByStatusPrato(status);
		return pratos;
	}
 
    public List<Prato> findByNomeContainingIgnoreCase(String nome) {
        return pratoRepository.findByNomeContainingIgnoreCase(nome);
    }
 
    @Transactional
    public Prato create(Prato prato) {
        if (prato != null && prato.getNome() != null && !prato.getNome().isEmpty()) {
            prato.setStatusPrato("ATIVO");
            return pratoRepository.save(prato);
        }
        return null;
    }
 
    @Transactional
    public Prato editar(long id, Prato prato) {
        Optional<Prato> pratoExistente = pratoRepository.findById(id);
 
        if (pratoExistente.isPresent()) {
            Prato pratoAtualizado = pratoExistente.get();
            pratoAtualizado.setNome(prato.getNome());
            pratoAtualizado.setDescricao(prato.getDescricao());
            pratoAtualizado.setPrincipal(prato.getPrincipal());
            pratoAtualizado.setSecundario(prato.getSecundario());
            pratoAtualizado.setAcompanhamento(prato.getAcompanhamento());
            pratoAtualizado.setStatusPrato(prato.getStatusPrato());
 
            return pratoRepository.save(pratoAtualizado);
        }
        return null;
    }
    
    @Transactional
    public void delete(long id) {
        pratoRepository.deleteById(id);
    }
 
    @Transactional
    public Prato inativar(long id) {
        Optional<Prato> pratoExistente = pratoRepository.findById(id);
 
        if (pratoExistente.isPresent()) {
            Prato pratoAtualizado = pratoExistente.get();
            pratoAtualizado.setStatusPrato("INATIVO");
            return pratoRepository.save(pratoAtualizado);
        }
        return null;
    }
 
    @Transactional
    public Prato reativar(long id) {
        Optional<Prato> pratoExistente = pratoRepository.findById(id);
 
        if (pratoExistente.isPresent()) {
            Prato pratoAtualizado = pratoExistente.get();
            pratoAtualizado.setStatusPrato("ATIVO");
            return pratoRepository.save(pratoAtualizado);
        }
        return null;
    }
}
 