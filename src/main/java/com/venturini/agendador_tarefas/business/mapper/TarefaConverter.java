package com.venturini.agendador_tarefas.business.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.venturini.agendador_tarefas.business.dto.TarefaDTO;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    // TarefaDTO p/ -> TarefaEntity
    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    // TarefaEnity p/ -> TarefaDTO
    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);
}
