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
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // instanciar uma vez e reutilizar
    }

    private final String SENHA_PADRAO = "12345678";

    public Usuario findById(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findAllByStatus(String status) {
        return usuarioRepository.findByStatusUsuario(status);
    }

    public List<Usuario> findByNomeContainingIgnoreCase(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && !"INATIVO".equals(usuario.getStatusUsuario())) {
            String senhaArmazenada = usuario.getSenha();

            if (senhaArmazenada != null && senhaArmazenada.startsWith("$2a$")) {
                // senha com hash BCrypt: compara usando encoder.matches()
                if (passwordEncoder.matches(senha, senhaArmazenada)) {
                    return usuario;
                }
            } else {
                // Se senha não estiver criptografada (não recomendado)
                if (senha.equals(senhaArmazenada)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Cria conta com senha padrão criptografada com BCrypt
    @Transactional
    public Usuario create(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            return null; // email já cadastrado
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

        usuario.setSenha(senhaCriptografada);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setStatusUsuario("ATIVO");

        return usuarioRepository.save(usuario);
    }

    // Cria usuário com senha definida pelo usuário, criptografando com BCrypt
    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) == null) {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
            usuario.setDataCadastro(LocalDateTime.now());
            usuario.setStatusUsuario("ATIVO");
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Altera senha criptografando com BCrypt
    @Transactional
    public Usuario alterarSenha(long id, Usuario usuario) {
        Optional<Usuario> _usuario = usuarioRepository.findById(id);

        if (_usuario.isPresent()) {
            Usuario usuarioAtualizado = _usuario.get();
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

            usuarioAtualizado.setSenha(senhaCriptografada);
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

    // Inativa usuário e reseta senha padrão criptografada com BCrypt
    @Transactional
    public Usuario inativar(long id) {
        Optional<Usuario> _usuario = usuarioRepository.findById(id);

        if (_usuario.isPresent()) {
            Usuario usuarioAtualizado = _usuario.get();
            String senhaCriptografada = passwordEncoder.encode(SENHA_PADRAO);

            usuarioAtualizado.setSenha(senhaCriptografada);
            usuarioAtualizado.setDataCadastro(LocalDateTime.now());
            usuarioAtualizado.setStatusUsuario("INATIVO");

            return usuarioRepository.save(usuarioAtualizado);
        }
        return null;
    }

    // Reativa usuário e reseta senha padrão criptografada com BCrypt
    @Transactional
    public Usuario reativar(long id) {
        Optional<Usuario> _usuario = usuarioRepository.findById(id);

        if (_usuario.isPresent()) {
            Usuario usuarioAtualizado = _usuario.get();
            String senhaCriptografada = passwordEncoder.encode(SENHA_PADRAO);

            usuarioAtualizado.setSenha(senhaCriptografada);
            usuarioAtualizado.setDataCadastro(LocalDateTime.now());
            usuarioAtualizado.setStatusUsuario("ATIVO");

            return usuarioRepository.save(usuarioAtualizado);
        }
        return null;
    }

    // Reseta senha para padrão e força troca, senha criptografada com BCrypt
    @Transactional
    public Usuario resetarSenha(long id) {
        Optional<Usuario> _usuario = usuarioRepository.findById(id);

        if (_usuario.isPresent()) {
            Usuario usuarioAtualizado = _usuario.get();
            String senhaCriptografada = passwordEncoder.encode(SENHA_PADRAO);

            usuarioAtualizado.setSenha(senhaCriptografada);
            usuarioAtualizado.setDataCadastro(LocalDateTime.now());
            usuarioAtualizado.setStatusUsuario("TROCAR_SENHA");

            return usuarioRepository.save(usuarioAtualizado);
        }
        return null;
    }
}
