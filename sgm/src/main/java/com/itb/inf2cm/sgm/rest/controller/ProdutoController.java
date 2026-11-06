package com.itb.inf2cm.sgm.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itb.inf2cm.sgm.model.entity.Produto;
import com.itb.inf2cm.sgm.rest.dto.ProdutoDTO;
import com.itb.inf2cm.sgm.rest.mapper.ProdutoMapper;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable long id) {
        Produto produto = produtoService.findById(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        ProdutoDTO dto = ProdutoMapper.toDTO(produto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> dtos = produtoService.findAll()
            .stream()
            .map(ProdutoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findAllAtivos")
    public ResponseEntity<List<ProdutoDTO>> findAllAtivos() {
        List<ProdutoDTO> dtos = produtoService.findAllByStatus("ATIVO")
            .stream()
            .map(ProdutoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestPart("produto") ProdutoDTO dto) {

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setStatusProduto(dto.getStatusProduto());

        Produto _produto = produtoService.create(produto);
        if (_produto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Produto já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("Produto criado com sucesso!"));
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id, @ModelAttribute Produto produto) {

        Produto _produto = produtoService.editar(id, produto);
        if (_produto == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("Produto alterado com sucesso!"));
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        Produto _produto = produtoService.inativar(id);
        if (_produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Produto inativado com sucesso!"));
    }
    
    @PutMapping("/reativar/{id}")
    public ResponseEntity<?> reativar(@PathVariable long id) {
        Produto produto = produtoService.reativar(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Produto reativado com sucesso!"));
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<ProdutoDTO>> findByNome(@RequestParam String nome) {
        List<ProdutoDTO> dtos = produtoService.findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(ProdutoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
