package com.itb.inf2cm.sgm.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itb.inf2cm.sgm.model.entity.PratoProduto;
import com.itb.inf2cm.sgm.rest.dto.PratoProdutoDTO;
import com.itb.inf2cm.sgm.rest.mapper.PratoProdutoMapper;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.PratoProdutoService;

@RestController
@RequestMapping("/pratoProduto")
public class PratoProdutoController {

    private final PratoProdutoService pratoProdutoService;

    public PratoProdutoController(PratoProdutoService pratoProdutoService) {
        this.pratoProdutoService = pratoProdutoService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PratoProdutoDTO> findById(@PathVariable long id) {
        PratoProduto pratoProduto = pratoProdutoService.findById(id);
        if (pratoProduto == null) {
            return ResponseEntity.notFound().build();
        }
        PratoProdutoDTO dto = PratoProdutoMapper.toDTO(pratoProduto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PratoProdutoDTO>> findAll() {
        List<PratoProdutoDTO> dtos = pratoProdutoService.findAll()
            .stream()
            .map(PratoProdutoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findAllAtivos")
    public ResponseEntity<List<PratoProdutoDTO>> findAllAtivos() {
        List<PratoProdutoDTO> dtos = pratoProdutoService.findAllByStatus("ATIVO")
            .stream()
            .map(PratoProdutoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestPart("pratoProduto") PratoProdutoDTO dto) {

        PratoProduto pratoProduto = new PratoProduto();
        pratoProduto.setStatusPratoProduto(dto.getStatusPratoProduto());

        PratoProduto _pratoProduto = pratoProdutoService.create(pratoProduto);
        if (_pratoProduto == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("PratoProduto já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("PratoProduto criado com sucesso!"));
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id, @ModelAttribute PratoProduto pratoProduto) {

        PratoProduto _pratoProduto = pratoProdutoService.editar(id, pratoProduto);
        if (_pratoProduto == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("PratoProduto alterado com sucesso!"));
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        PratoProduto _pratoProduto = pratoProdutoService.inativar(id);
        if (_pratoProduto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("PratoProduto inativado com sucesso!"));
    }
    
    @PutMapping("/reativar/{id}")
    public ResponseEntity<?> reativar(@PathVariable long id) {
        PratoProduto pratoProduto = pratoProdutoService.reativar(id);
        if (pratoProduto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("PratoProduto reativado com sucesso!"));
    }
    
}
