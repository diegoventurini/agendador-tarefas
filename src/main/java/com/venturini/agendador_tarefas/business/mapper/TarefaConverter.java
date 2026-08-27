package com.venturini.agendador_tarefas.business.mapper;

import com.venturini.agendador_tarefas.business.dto.TarefaDTO;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    // TarefaDTO p/ -> TarefaEntity
    TarefaEntity paraTarefaEntity(TarefaDTO tarefaDTO);

    // TarefaEntity p/ -> TarefaDTO
    TarefaDTO paraTarefaDTO(TarefaEntity tarefaEntity);

    // TarefaDTO p/ -> TarefaEntity
    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTO> tarefaDTOS);

   // TarefaEntity p/ -> TarefaDTO
    List<TarefaDTO> paraListaTarefaDTO(List<TarefaEntity> tarefaEntities);



}
