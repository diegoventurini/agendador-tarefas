package com.venturini.agendador_tarefas.infrastructure.controller;

import com.venturini.agendador_tarefas.business.TarefaService;
import com.venturini.agendador_tarefas.business.dto.TarefaDTO;
import com.venturini.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravaTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscarListaTarefaAgendadaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaListaTarefaAgendadaPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarListaTarefaPorEmail(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefaService.buscaListaTarefaPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id){
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alterarStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum statusNotificacaoEnum,
                                                              @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatusNotificacao(statusNotificacaoEnum, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizarTarefas(@RequestBody TarefaDTO tarefaDTO,
                                                      @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDTO, id));
    }

}
