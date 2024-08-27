package com.gym.crud.services;

import com.gym.crud.dtos.AlunoRecordDto;
import com.gym.crud.models.AlunoModel;
import com.gym.crud.models.PlanoModel;
import com.gym.crud.models.FichaTreinoModel;
import com.gym.crud.repositories.AlunoRepository;
import com.gym.crud.repositories.PlanoRepository;
import com.gym.crud.repositories.FichaTreinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final FichaTreinoRepository fichaTreinoRepository;

    public AlunoService(AlunoRepository alunoRepository, PlanoRepository planoRepository,
                        FichaTreinoRepository fichaTreinoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.fichaTreinoRepository = fichaTreinoRepository;
    }

    @Transactional(readOnly = true)
    public List<AlunoModel> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AlunoModel findAlunoById(UUID id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + id));
    }

    @Transactional
    public AlunoModel saveAluno(AlunoRecordDto alunoRecordDto) {
        AlunoModel alunoModel = new AlunoModel();
        setAlunoFields(alunoModel, alunoRecordDto);
        return alunoRepository.save(alunoModel);
    }

    @Transactional
    public AlunoModel updateAluno(UUID alunoId, AlunoRecordDto alunoRecordDto) {
        AlunoModel alunoModel = findAlunoById(alunoId);
        setAlunoFields(alunoModel, alunoRecordDto);
        return alunoRepository.save(alunoModel);
    }

    @Transactional
    public void deleteAluno(UUID id) {
        AlunoModel aluno = findAlunoById(id);
        alunoRepository.delete(aluno);
    }

    private void setAlunoFields(AlunoModel alunoModel, AlunoRecordDto alunoRecordDto) {
        alunoModel.setNome(alunoRecordDto.nome());
        alunoModel.setEmail(alunoRecordDto.email());
        alunoModel.setTelefone(alunoRecordDto.telefone());
        setPlano(alunoModel, alunoRecordDto.planoId());
    }

    private void setPlano(AlunoModel alunoModel, UUID planoId) {
        PlanoModel plano = planoRepository.findById(planoId)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado com ID: " + planoId));
        alunoModel.setPlano(plano);
    }

    private void setFichasTreino(AlunoModel alunoModel, Set<UUID> fichaTreinoIds) {
        Set<FichaTreinoModel> fichasTreino = new HashSet<>();
        for (UUID fichaTreinoId : fichaTreinoIds) {
            FichaTreinoModel fichaTreino = fichaTreinoRepository.findById(fichaTreinoId)
                    .orElseThrow(() -> new IllegalArgumentException("Ficha de treino não encontrada com ID: " + fichaTreinoId));
            fichasTreino.add(fichaTreino);
        }
        alunoModel.setFichasTreino(fichasTreino);
    }
}
