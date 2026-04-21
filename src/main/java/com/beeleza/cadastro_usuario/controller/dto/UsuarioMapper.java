package com.beeleza.cadastro_usuario.controller.dto;

import com.beeleza.cadastro_usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Usuário não pode ser null");
        }
        return Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .build();
    }

    public UsuarioResponse toResponse(Usuario entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity não pode ser null");
        }
        return UsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .build();
    }
}