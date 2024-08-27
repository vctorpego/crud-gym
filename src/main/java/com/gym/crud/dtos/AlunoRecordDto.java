package com.gym.crud.dtos;

import java.util.UUID;

public record AlunoRecordDto(
        String nome,
        String email,
        String telefone,
        UUID planoId
) {}
