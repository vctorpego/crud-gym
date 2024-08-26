package com.gym.crud.services;

import com.gym.crud.dtos.FichaTreinoRecordDto;
import com.gym.crud.models.AlunoModel;
import com.gym.crud.models.FichaTreinoModel;
import com.gym.crud.repositories.AlunoRepository;
import com.gym.crud.repositories.FichaTreinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class FichaTreinoService {

    private final FichaTreinoRepository fichaTreinoRepository;
    private final AlunoRepository alunoRepository;

    public FichaTreinoService(FichaTreinoRepository fichaTreinoRepository, AlunoRepository alunoRepository) {
        this.fichaTreinoRepository = fichaTreinoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional(readOnly = true)
    public List<FichaTreinoModel> getAllFichasTreino() {
        return fichaTreinoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public FichaTreinoModel findFichaTreinoById(UUID id) {
        return fichaTreinoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ficha de Treino não encontrada com ID: " + id));
    }

    @Transactional
    public FichaTreinoModel saveFichaTreino(FichaTreinoRecordDto fichaTreinoRecordDto) {
        FichaTreinoModel fichaTreinoModel = new FichaTreinoModel();
        setFichaTreinoFields(fichaTreinoModel, fichaTreinoRecordDto);
        return fichaTreinoRepository.save(fichaTreinoModel);
    }

    @Transactional
    public FichaTreinoModel updateFichaTreino(UUID fichaTreinoId, FichaTreinoRecordDto fichaTreinoRecordDto) {
        FichaTreinoModel fichaTreinoModel = findFichaTreinoById(fichaTreinoId);
        setFichaTreinoFields(fichaTreinoModel, fichaTreinoRecordDto);
        return fichaTreinoRepository.save(fichaTreinoModel);
    }

    @Transactional
    public void deleteFichaTreino(UUID id) {
        FichaTreinoModel fichaTreino = findFichaTreinoById(id);
        fichaTreinoRepository.delete(fichaTreino);
    }

    private void setFichaTreinoFields(FichaTreinoModel fichaTreinoModel, FichaTreinoRecordDto fichaTreinoRecordDto) {
        fichaTreinoModel.setData(fichaTreinoRecordDto.data());
        fichaTreinoModel.setExercicios(fichaTreinoRecordDto.exercicios());
        setAlunos(fichaTreinoModel, fichaTreinoRecordDto.alunoIds());
    }

    private void setAlunos(FichaTreinoModel fichaTreinoModel, Set<UUID> alunoIds) {
        Set<AlunoModel> alunos = new HashSet<>();
        for (UUID alunoId : alunoIds) {
            AlunoModel aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
            alunos.add(aluno);
        }
        fichaTreinoModel.setAlunos(alunos);
    }
}
