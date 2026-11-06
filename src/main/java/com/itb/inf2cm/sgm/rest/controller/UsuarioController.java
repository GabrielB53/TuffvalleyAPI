package com.itb.inf2cm.sgm.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itb.inf2cm.sgm.model.entity.Usuario;
import com.itb.inf2cm.sgm.rest.dto.UsuarioDTO;
import com.itb.inf2cm.sgm.rest.mapper.UsuarioMapper;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDTO dto = UsuarioMapper.toDTO(usuario);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> dtos = usuarioService.findAll()
            .stream()
            .map(UsuarioMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/findAllAtivos")
    public ResponseEntity<List<UsuarioDTO>> findAllAtivos() {
        List<UsuarioDTO> dtos = usuarioService.findAllByStatus("ATIVO")
            .stream()
            .map(UsuarioMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        Usuario _usuario = usuarioService.save(usuario);
        if (_usuario == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MessageResponse("Usuário já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("Conta de Usuário criada com sucesso!"));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        Usuario _usuario = usuarioService.create(usuario);
        if (_usuario == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new MessageResponse("Usuário já existe"));
        }
        return ResponseEntity.ok(new MessageResponse("Conta de Usuário criada com sucesso!"));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable long id,
                                   @RequestParam(required = false) MultipartFile file,
                                   @ModelAttribute Usuario usuario) {

        Usuario _usuario = usuarioService.editar(file, id, usuario);
        if (_usuario == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("Usuário alterado com sucesso!"));
    }
    @PutMapping("/editarPerfil/{id}")
    public ResponseEntity<?> editarPerfil(@PathVariable long id,
                                   @RequestParam(required = false) MultipartFile file,
                                   @ModelAttribute Usuario usuario) {

        Usuario _usuario = usuarioService.editarPerfil(file, id, usuario);
        if (_usuario == null) {
            return ResponseEntity.notFound()
                .build();
        }
        return ResponseEntity.ok(new MessageResponse("Usuário alterado com sucesso!"));
    }

    @PutMapping("/alterarSenha/{id}")
    public ResponseEntity<?> trocarSenha(@PathVariable long id, @RequestBody Usuario usuario) {
        Usuario _usuario = usuarioService.alterarSenha(id, usuario);
        if (_usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Senha alterada com sucesso!"));
    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable long id) {
        Usuario _usuario = usuarioService.inativar(id);
        if (_usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Conta de Usuário inativada com sucesso!"));
    }
    
    @PutMapping("/reativar/{id}")
    public ResponseEntity<?> reativar(@PathVariable long id) {
        Usuario usuario = usuarioService.reativar(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Conta de Usuário reativada com sucesso!"));
    }
    @PutMapping("/resetarSenha/{id}")
    public ResponseEntity<?> resetarSenha(@PathVariable long id) {
        Usuario usuario = usuarioService.resetarSenha(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new MessageResponse("Senha resetada com sucesso!"));
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario _usuario = usuarioService.login(usuario.getEmail(), usuario.getSenha());
        if (_usuario != null) {
            UsuarioDTO dto = UsuarioMapper.toDTO(_usuario);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Dados Incorretos!"));
    }
    
    @GetMapping("/findByNome")
    public List<Usuario> findByNome(@RequestParam String nome) {
        return usuarioService.findByNomeContainingIgnoreCase(nome);
    }
}
