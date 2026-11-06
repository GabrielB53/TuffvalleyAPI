package com.itb.inf2cm.sgm.rest.controller;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.itb.inf2cm.sgm.model.entity.Prato;
import com.itb.inf2cm.sgm.rest.dto.PratoDTO;
import com.itb.inf2cm.sgm.rest.mapper.PratoMapper;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.PratoService;
 
@RestController
@RequestMapping("/prato")
public class PratoController {
 
    private final PratoService pratoService;
 
    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }
 
    @GetMapping("/findById/{id}")
    public ResponseEntity<PratoDTO> findById(@PathVariable long id) {
        Prato prato = pratoService.findById(id);
        if (prato == null) {
            return ResponseEntity.notFound().build();
        }
        PratoDTO dto = PratoMapper.toDTO(prato);
        return ResponseEntity.ok(dto);
    }
 
    @GetMapping("/findAll")
    public ResponseEntity<List<PratoDTO>> findAll() {
        List<PratoDTO> dtos = pratoService.findAll()
            .stream()
            .map(PratoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
 
    @GetMapping("/findAllAtivos")
    public ResponseEntity<List<PratoDTO>> findAllAtivos() {
        List<PratoDTO> dtos = pratoService.findAllByStatus("ATIVO")
            .stream()
            .map(PratoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
 
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestPart("prato") PratoDTO dto) {
 
        Prato prato = new Prato();
        prato.setNome(dto.getNome());
        prato.setStatusPrato(dto.getStatusPrato());
 
        Prato _prato = pratoService.create(prato);
        if (_prato == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Prato já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("Prato criado com sucesso!"));
    }
 
 
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id, @ModelAttribute Prato prato) {
 
        Prato _prato = pratoService.editar(id, prato);
        if (_prato == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("Prato alterado com sucesso!"));
    }
 
    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        Prato _prato = pratoService.inativar(id);
        if (_prato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Prato inativado com sucesso!"));
    }
    
    @PutMapping("/reativar/{id}")
    public ResponseEntity<?> reativar(@PathVariable long id) {
        Prato prato = pratoService.reativar(id);
        if (prato == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Prato reativado com sucesso!"));
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<PratoDTO>> findByNome(@RequestParam String nome) {
        List<PratoDTO> dtos = pratoService.findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(PratoMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}