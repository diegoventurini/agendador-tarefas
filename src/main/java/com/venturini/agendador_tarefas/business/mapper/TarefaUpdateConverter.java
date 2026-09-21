package com.venturini.agendador_tarefas.business.mapper;

import com.venturini.agendador_tarefas.business.dto.TarefaDTORecord;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
                                    /// for nulo pega os dados da outra
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
                       // Se este for nulo ------------->  usa esse
    void updateTarefa(TarefaDTORecord tarefaDTO, @MappingTarget TarefaEntity tarefaEntity);
}
