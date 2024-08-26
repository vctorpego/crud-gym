package com.gym.crud.dtos;

import java.util.Set;
import java.util.UUID;

public record PlanoRecordDto(
        String nomePlano,
        double valor,
        Set<UUID> alunoIds
) {}
