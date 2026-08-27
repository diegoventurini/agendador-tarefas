package com.venturini.agendador_tarefas.infrastructure.repository;

import com.venturini.agendador_tarefas.business.dto.TarefaDTO;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {

    // Buscar Tarefas agendadas por um certo período de tempo
    List<TarefaEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefaEntity> findByEmailUsuario(String email);
}
