package com.itb.inf2cm.sgm.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itb.inf2cm.sgm.model.entity.Mensagem;
import com.itb.inf2cm.sgm.rest.response.MessageResponse;
import com.itb.inf2cm.sgm.service.MensagemService;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

	private MensagemService mensagemService;
	// Source -> Generate Constructor using Fields...
	public MensagemController(MensagemService mensagemService) {
		super();
		this.mensagemService = mensagemService;
	}
		
	@GetMapping("/findById/{id}")
	public ResponseEntity<Mensagem> findById(@PathVariable long id){
		Mensagem mensagem = mensagemService.findById(id);	
		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);	
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Mensagem>> findAll(){
		
		List<Mensagem> mensagens = mensagemService.findAll();
		
		return new ResponseEntity<List<Mensagem>>(mensagens, HttpStatus.OK);
		
	}
	
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<List<Mensagem>> findByEmail(@PathVariable String email){
		
		List<Mensagem> mensagens = mensagemService.findByEmail(email);
		
		return new ResponseEntity<List<Mensagem>>(mensagens, HttpStatus.OK);
		
	}
	
	@GetMapping("/findAllAtivos")
	public ResponseEntity<List<Mensagem>> findAllAtivos(){
		
		List<Mensagem> mensagens = mensagemService.findAllByStatus("ATIVO");
		
		return new ResponseEntity<List<Mensagem>>(mensagens, HttpStatus.OK);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Mensagem mensagem) {
		
		Mensagem _mensagem = mensagemService.create(mensagem);
		
		return ResponseEntity.ok()
				.body(new MessageResponse("Mensagem enviada com sucesso!"));
	}
	
	@PutMapping("/inativar/{id}")
	public ResponseEntity<?> inativar(@PathVariable long id) {
		
		Mensagem _mensagem = mensagemService.inativar(id);
		
		return ResponseEntity.ok()
				.body(new MessageResponse("Mensagem lida!"));
	}
}








