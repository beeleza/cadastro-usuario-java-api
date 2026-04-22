package com.beeleza.cadastro_usuario.business;

import com.beeleza.cadastro_usuario.business.exception.EmailJaExistenteException;
import com.beeleza.cadastro_usuario.business.exception.UsuarioNaoEncontradoException;
import com.beeleza.cadastro_usuario.controller.dto.UsuarioMapper;
import com.beeleza.cadastro_usuario.controller.dto.UsuarioRequest;
import com.beeleza.cadastro_usuario.controller.dto.UsuarioResponse;
import com.beeleza.cadastro_usuario.infrastructure.entity.Usuario;
import com.beeleza.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioResponse salvarUsuario(UsuarioRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailJaExistenteException("E-mail já cadastrado");
        }
        Usuario entity = usuarioMapper.toEntity(request);
        Usuario saved = usuarioRepository.save(entity);
        return usuarioMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarUsuarioPorEmail(String email) {
        Usuario entity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("E-mail não encontrado"));
        return usuarioMapper.toResponse(entity);
    }

    @Transactional
    public void deletarUsuarioPorEmail(String email) {
        Usuario entity = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("E-mail não encontrado"));
        usuarioRepository.delete(entity);
    }

    @Transactional
    public UsuarioResponse atualizarUsuario(Integer id, UsuarioRequest request) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        if (request.getEmail() != null && !request.getEmail().equals(entity.getEmail())) {
            usuarioRepository.findByEmail(request.getEmail())
                    .ifPresent(u -> { throw new EmailJaExistenteException("E-mail já cadastrado"); });
            entity.setEmail(request.getEmail());
        }
        if (request.getNome() != null) {
            entity.setNome(request.getNome());
        }
        Usuario updated = usuarioRepository.save(entity);
        return usuarioMapper.toResponse(updated);
    }
}