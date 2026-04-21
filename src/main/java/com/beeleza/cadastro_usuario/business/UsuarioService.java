package com.beeleza.cadastro_usuario.business;

import com.beeleza.cadastro_usuario.controller.dto.UsuarioMapper;
import com.beeleza.cadastro_usuario.controller.dto.UsuarioRequest;
import com.beeleza.cadastro_usuario.controller.dto.UsuarioResponse;
import com.beeleza.cadastro_usuario.infrastructure.entity.Usuario;
import com.beeleza.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponse salvarUsuario(UsuarioRequest request) {
        Usuario entity = usuarioMapper.toEntity(request);
        Usuario saved = usuarioRepository.saveAndFlush(entity);
        return usuarioMapper.toResponse(saved);
    }

    public UsuarioResponse buscarUsuarioPorEmail(String email) {
        Usuario entity = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("E-mail não encontrado")
        );
        return usuarioMapper.toResponse(entity);
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioResponse atualizarUsuario(Integer id, UsuarioRequest request) {
        Usuario entity = usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        if (request.getNome() != null) {
            entity.setNome(request.getNome());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        Usuario updated = usuarioRepository.saveAndFlush(entity);
        return usuarioMapper.toResponse(updated);
    }
}