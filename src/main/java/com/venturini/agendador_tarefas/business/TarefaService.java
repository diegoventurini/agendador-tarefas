package com.venturini.agendador_tarefas.business;

import com.venturini.agendador_tarefas.business.dto.TarefaDTORecord;
import com.venturini.agendador_tarefas.business.mapper.TarefaConverter;
import com.venturini.agendador_tarefas.business.mapper.TarefaUpdateConverter;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.venturini.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.venturini.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.venturini.agendador_tarefas.infrastructure.repository.TarefaRepository;
import com.venturini.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTORecord gravaTarefa(TarefaDTORecord tarefaDTO, String token) {

        String email = jwtUtil.extractUsername(token.substring(7));

        // Construtor (set)
        TarefaDTORecord tarefaDTORecord =
                new TarefaDTORecord(null, tarefaDTO.nomeTarefa(), tarefaDTO.descricao(),
                       LocalDateTime.now(), tarefaDTO.dataEvento(), null,
                        email, StatusNotificacaoEnum.PENDENTE);

        // tarefaDTO.setDataCriacao(LocalDateTime.now());
        // tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        // tarefaDTO.setEmailUsuario(email);

        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTORecord);

        return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(tarefaEntity));

    }

    public List<TarefaDTORecord> buscaListaTarefaAgendadaPorPeriodo(LocalDateTime dataInical, LocalDateTime dataFinal) {

        return tarefaConverter.paraListaTarefaDTORecord(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInical, dataFinal,
                                                                                StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefaDTORecord> buscaListaTarefaPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefaConverter.paraListaTarefaDTORecord(tarefaRepository.findByEmailUsuario(email));
    }

    //No Mongo, pode ser deletado pelo Id, sem criar parametros na repository
    // Deletar Tarefa por ID
    public void deletaTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    "Erro ao deletar tarefa por id: id não existe! -> " + id, e.getCause());
        }
    }

    public TarefaDTORecord alteraStatusNotificacao(StatusNotificacaoEnum statusNotificacaoEnum, String id) {
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Tarefa não encontrada." + id));
            tarefaEntity.setStatusNotificacaoEnum(statusNotificacaoEnum);
            return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(tarefaEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa. " + e.getCause());
        }
    }

    public TarefaDTORecord updateTarefas(TarefaDTORecord tarefaDTO, String id) {
        try {
            TarefaEntity tarefaEntity = tarefaRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Tarefa não encontrada, " + id));

            tarefaUpdateConverter.updateTarefa(tarefaDTO, tarefaEntity);
            return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(tarefaEntity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao modificar a tarefa. " + e.getCause());
        }
    }
}
