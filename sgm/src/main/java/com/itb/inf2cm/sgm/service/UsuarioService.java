package com.itb.inf2cm.sgm.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.itb.inf2cm.sgm.model.entity.Usuario;

import com.itb.inf2cm.sgm.model.repository.UsuarioRepository;


@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	private final String SENHA_PADRAO = "12345678";

	public Usuario findById(long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			return usuario.get();
		}

		return null;
	}

	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
	
	public List<Usuario> findAllByStatus(String status) {
		List<Usuario> usuarios = usuarioRepository.findByStatusUsuario(status);
		return usuarios;
	}
	public List<Usuario> findByNomeContainingIgnoreCase(String nome) {
	    return usuarioRepository.findByNomeContainingIgnoreCase(nome);
	}

	@Transactional
	public Usuario login(String email, String senha) {
	    Usuario usuario = usuarioRepository.findByEmail(email);

	    if (usuario != null) {
	        if (!usuario.getStatusUsuario().equals("INATIVO")) {
	            PasswordEncoder encoder = new BCryptPasswordEncoder();

	            if (encoder.matches(senha, usuario.getSenha())) {
	                return usuario;
	            }
	        }
	    }
	    return null;
	}
	
	public Usuario findByEmail(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario != null) {
			return usuario;
		}
		return null;
	}
	


	// A CONTA DE USUÁRIO SERÁ CRIADA COM UMA SENHA PADRÃO
	// ELE DEVE ALTERAR NO PRIMEIRO ACESSO
	@Transactional
	public Usuario create(Usuario usuario) {
	    Usuario existente = usuarioRepository.findByEmail(usuario.getEmail());

	    if (existente != null) {
	        // Email já cadastrado
	        return null;
	    }

	    // Criptografar a senha padrão com BCrypt
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    String senhaCriptografada = encoder.encode(SENHA_PADRAO);

	    usuario.setSenha(senhaCriptografada);
	    usuario.setDataCadastro(LocalDateTime.now());
	    usuario.setStatusUsuario("TROCAR_SENHA");

	    return usuarioRepository.save(usuario);
	}

	// A CONTA DE USUÁRIO SERÁ CRIADA COM SENHA DEFINIDA POR ELE
	@Transactional
	public Usuario save(Usuario usuario) {

		Usuario _usuario = usuarioRepository.findByEmail(usuario.getEmail());

		if (_usuario == null) {

			String senha = Base64.getEncoder()
									.encodeToString(usuario.getSenha().getBytes());

			usuario.setSenha(senha);
			usuario.setDataCadastro(LocalDateTime.now());
			usuario.setStatusUsuario("ATIVO");

			return usuarioRepository.save(usuario);
		}
		return null;
	}
	
	@Transactional
	public Usuario alterarSenha(long id, Usuario usuario) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);

		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();
			String senha = Base64.getEncoder()
						.encodeToString(usuario.getSenha().getBytes());

			usuarioAtualizado.setSenha(senha);
			usuarioAtualizado.setDataCadastro(LocalDateTime.now());
			usuarioAtualizado.setStatusUsuario("ATIVO");

			return usuarioRepository.save(usuarioAtualizado);
		}
		return null;
	}
	

	@Transactional
	public Usuario editar(MultipartFile file, long id, Usuario usuario) {
	    Optional<Usuario> _usuario = usuarioRepository.findById(id);

	    if (_usuario.isPresent()) {
	        Usuario usuarioAtualizado = _usuario.get();

	        usuarioAtualizado.setNome(usuario.getNome());
	        usuarioAtualizado.setEmail(usuario.getEmail());
	        usuarioAtualizado.setStatusUsuario(usuario.getStatusUsuario());
	        usuarioAtualizado.setNivelAcesso(usuario.getNivelAcesso());

	        if (file != null && file.getSize() > 0) {
	            try {
	                usuarioAtualizado.setFoto(file.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } 

	        return usuarioRepository.save(usuarioAtualizado);
	    }
	    return null;
	}
	
	@Transactional
	public Usuario editarPerfil(MultipartFile file, long id, Usuario usuario) {
	    Optional<Usuario> _usuario = usuarioRepository.findById(id);

	    if (_usuario.isPresent()) {
	        Usuario usuarioAtualizado = _usuario.get();

	        usuarioAtualizado.setNome(usuario.getNome());
	        usuarioAtualizado.setNivelAcesso(usuario.getNivelAcesso());

	        if (file != null && file.getSize() > 0) {
	            try {
	                usuarioAtualizado.setFoto(file.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } 

	        return usuarioRepository.save(usuarioAtualizado);
	    }
	    return null;
	}

	
	@Transactional
	public Usuario inativar(long id) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);

		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();
			String senha = Base64.getEncoder().encodeToString(SENHA_PADRAO.getBytes());

			usuarioAtualizado.setSenha(senha);
			usuarioAtualizado.setDataCadastro(LocalDateTime.now());
			usuarioAtualizado.setStatusUsuario("INATIVO");

			return usuarioRepository.save(usuarioAtualizado);
		}
		return null;
	}

	@Transactional
	public Usuario reativar(long id) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);

		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();
			String senha = Base64.getEncoder()
						.encodeToString(SENHA_PADRAO.getBytes());

			usuarioAtualizado.setSenha(senha);
			usuarioAtualizado.setDataCadastro(LocalDateTime.now());
			usuarioAtualizado.setStatusUsuario("ATIVO");

			return usuarioRepository.save(usuarioAtualizado);
		}
		return null;
	}

	@Transactional
	public Usuario resetarSenha(long id) {
		Optional<Usuario> _usuario = usuarioRepository.findById(id);

		if (_usuario.isPresent()) {
			Usuario usuarioAtualizado = _usuario.get();
			String senha = Base64.getEncoder()
						.encodeToString(SENHA_PADRAO.getBytes());

			usuarioAtualizado.setSenha(senha);
			usuarioAtualizado.setDataCadastro(LocalDateTime.now());
			usuarioAtualizado.setStatusUsuario("TROCAR_SENHA");

			return usuarioRepository.save(usuarioAtualizado);
		}
		return null;
	}



}
