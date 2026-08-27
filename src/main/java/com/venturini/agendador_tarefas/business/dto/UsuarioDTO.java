package com.venturini.agendador_tarefas.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    // Esconder informações -> usa-se o DTO
    private String email;
    private String senha;
}
