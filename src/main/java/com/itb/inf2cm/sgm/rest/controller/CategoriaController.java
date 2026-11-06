package com.itb.inf2cm.sgm.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itb.inf2cm.sgm.model.entity.Categoria;
import com.itb.inf2cm.sgm.rest.dto.CategoriaDTO;
import com.itb.inf2cm.sgm.rest.mapper.CategoriaMapper;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable long id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        CategoriaDTO dto = CategoriaMapper.toDTO(categoria);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> dtos = categoriaService.findAll()
            .stream()
            .map(CategoriaMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findAllAtivos")
    public ResponseEntity<List<CategoriaDTO>> findAllAtivos() {
        List<CategoriaDTO> dtos = categoriaService.findAllByStatus("ATIVO")
            .stream()
            .map(CategoriaMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestPart("categoria") CategoriaDTO dto) {

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setStatusCategoria(dto.getStatusCategoria());

        Categoria _categoria = categoriaService.create(categoria);
        if (_categoria == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Categoria já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("Categoria criado com sucesso!"));
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id, @ModelAttribute Categoria categoria) {

        Categoria _categoria = categoriaService.editar(id, categoria);
        if (_categoria == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("Categoria alterado com sucesso!"));
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        Categoria _categoria = categoriaService.inativar(id);
        if (_categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Categoria inativado com sucesso!"));
    }
    
    @PutMapping("/reativar/{id}")
    public ResponseEntity<?> reativar(@PathVariable long id) {
        Categoria categoria = categoriaService.reativar(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Categoria reativado com sucesso!"));
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<CategoriaDTO>> findByNome(@RequestParam String nome) {
        List<CategoriaDTO> dtos = categoriaService.findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(CategoriaMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
