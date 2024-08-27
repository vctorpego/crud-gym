package com.gym.crud.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record FichaTreinoRecordDto(
        Date data,
        List<String> exercicios,
        UUID alunoId
) {}
