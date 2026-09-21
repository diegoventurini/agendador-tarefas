package com.venturini.agendador_tarefas.business.mapper;

import com.venturini.agendador_tarefas.business.dto.TarefaDTORecord;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    // TarefaDTORecord p/ -> TarefaEntity
    TarefaEntity paraTarefaEntity(TarefaDTORecord tarefaDTO);

    // TarefaEntity p/ -> TarefaDTORecord
    TarefaDTORecord paraTarefaDTORecord(TarefaEntity tarefaEntity);

    // TarefaDTORecord p/ -> TarefaEntity
    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTORecord> tarefaDTOS);

   // TarefaEntity p/ -> TarefaDTORecord
    List<TarefaDTORecord> paraListaTarefaDTORecord(List<TarefaEntity> tarefaEntities);



}
