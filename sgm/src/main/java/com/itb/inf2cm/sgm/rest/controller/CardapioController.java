package com.itb.inf2cm.sgm.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itb.inf2cm.sgm.model.entity.Cardapio;
import com.itb.inf2cm.sgm.model.entity.Prato;
import com.itb.inf2cm.sgm.rest.dto.CardapioDTO;
import com.itb.inf2cm.sgm.rest.mapper.CardapioMapper;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.CardapioService;
import com.itb.inf2cm.sgm.service.PratoService;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    private final CardapioService cardapioService;
    private final PratoService pratoService;

    public CardapioController(CardapioService cardapioService, PratoService pratoService) {
        this.cardapioService = cardapioService;
        this.pratoService = pratoService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CardapioDTO> findById(@PathVariable long id) {
        Cardapio cardapio = cardapioService.findById(id);
        if (cardapio == null) {
            return ResponseEntity.notFound().build();
        }
        CardapioDTO dto = CardapioMapper.toDTO(cardapio);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CardapioDTO>> findAll() {
        List<CardapioDTO> dtos = cardapioService.findAll()
            .stream()
            .map(CardapioMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findAllAtivos")
    public ResponseEntity<List<CardapioDTO>> findAllAtivos() {
        List<CardapioDTO> dtos = cardapioService.findAllByStatus("ATIVO")
            .stream()
            .map(CardapioMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestPart("cardapio") CardapioDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Prato prato = pratoService.findById(dto.getPratoId());
        if (prato == null) {
            return ResponseEntity.badRequest()
                .body(new MessageResponse("Prato não encontrado"));
        }

        Cardapio cardapio = new Cardapio();
        cardapio.setNome(dto.getNome());
        cardapio.setDiaServido(dto.getDiaServido());
        cardapio.setStatusCardapio(dto.getStatusCardapio());
        cardapio.setPrato(prato);

        if (file != null && !file.isEmpty()) {
            try {
                cardapio.setFoto(file.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MessageResponse("Erro ao processar arquivo"));
            }
        }

        Cardapio _cardapio = cardapioService.create(cardapio);
        if (_cardapio == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Cardápio já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("Cardápio criado com sucesso!"));
    }


    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id,
                                   @RequestParam(required = false) MultipartFile file,
                                   @ModelAttribute Cardapio cardapio) {

        Cardapio _cardapio = cardapioService.editar(file, id, cardapio);
        if (_cardapio == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("Usuário alterado com sucesso!"));
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        Cardapio _cardapio = cardapioService.inativar(id);
        if (_cardapio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Cardápio inativado com sucesso!"));
    }
    
    @PutMapping("/reativar/{id}")
    public ResponseEntity<?> reativar(@PathVariable long id) {
        Cardapio cardapio = cardapioService.reativar(id);
        if (cardapio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Cardápio reativado com sucesso!"));
    }
    
    @GetMapping("/findByNome")
    public ResponseEntity<List<CardapioDTO>> findByNome(@RequestParam String nome) {
        List<CardapioDTO> dtos = cardapioService.findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(CardapioMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
