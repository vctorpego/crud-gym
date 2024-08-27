package com.gym.crud.services;

import com.gym.crud.dtos.PlanoRecordDto;
import com.gym.crud.models.PlanoModel;
import com.gym.crud.models.AlunoModel;
import com.gym.crud.repositories.PlanoRepository;
import com.gym.crud.repositories.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final AlunoRepository alunoRepository;

    public PlanoService(PlanoRepository planoRepository, AlunoRepository alunoRepository) {
        this.planoRepository = planoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional(readOnly = true)
    public List<PlanoModel> getAllPlanos() {
        return planoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PlanoModel findPlanoById(UUID id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado com ID: " + id));
    }

    @Transactional
    public PlanoModel savePlano(PlanoRecordDto planoRecordDto) {
        PlanoModel planoModel = new PlanoModel();
        setPlanoFields(planoModel, planoRecordDto);
        return planoRepository.save(planoModel);
    }

    @Transactional
    public PlanoModel updatePlano(UUID planoId, PlanoRecordDto planoRecordDto) {
        PlanoModel planoModel = findPlanoById(planoId);
        setPlanoFields(planoModel, planoRecordDto);
        return planoRepository.save(planoModel);
    }

    @Transactional
    public void deletePlano(UUID id) {
        PlanoModel plano = findPlanoById(id);
        planoRepository.delete(plano);
    }

    private void setPlanoFields(PlanoModel planoModel, PlanoRecordDto planoRecordDto) {
        planoModel.setNomePlano(planoRecordDto.nomePlano());
        planoModel.setValor(planoRecordDto.valor());
    }

    private void setAlunos(PlanoModel planoModel, Set<UUID> alunoIds) {
        Set<AlunoModel> alunos = new HashSet<>();
        for (UUID alunoId : alunoIds) {
            AlunoModel aluno = alunoRepository.findById(alunoId)
                    .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
            alunos.add(aluno);
        }
        planoModel.setAlunos(alunos);
    }
}
