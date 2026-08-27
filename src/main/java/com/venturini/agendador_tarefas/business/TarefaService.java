package com.venturini.agendador_tarefas.business;

import com.venturini.agendador_tarefas.business.dto.TarefaDTO;
import com.venturini.agendador_tarefas.business.mapper.TarefaConverter;
import com.venturini.agendador_tarefas.infrastructure.entity.TarefaEntity;
import com.venturini.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.venturini.agendador_tarefas.infrastructure.repository.TarefaRepository;
import com.venturini.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravaTarefa(TarefaDTO tarefaDTO, String token) {

        String email = jwtUtil.extractUsername(token.substring(7));

        tarefaDTO.setDataCriacao(LocalDateTime.now());

        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        tarefaDTO.setEmailUsuario(email);

        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);

        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));

    }
}
