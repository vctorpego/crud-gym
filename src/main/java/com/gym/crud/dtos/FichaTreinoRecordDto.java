package com.gym.crud.dtos;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record FichaTreinoRecordDto(
        String data,
        List<String> exercicios,
        Set<UUID> alunoIds
) {}
