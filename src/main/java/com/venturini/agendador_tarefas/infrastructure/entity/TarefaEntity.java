package com.venturini.agendador_tarefas.infrastructure.entity;

import com.venturini.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// NoSql -> nome da tabela
@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaEntity {

    @Id
    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private LocalDateTime dataAlteracao;
    private String emailUsuario;
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
