package com.beeleza.cadastro_usuario.controller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {
    private Integer id;
    private String nome;
    private String email;
}