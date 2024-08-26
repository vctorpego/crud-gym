package com.gym.crud.controllers;

import com.gym.crud.dtos.AlunoRecordDto;
import com.gym.crud.models.AlunoModel;
import com.gym.crud.services.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gym/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> getAllAlunos() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAllAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> findAlunoById(@PathVariable UUID id) {
        AlunoModel aluno = alunoService.findAlunoById(id);
        return ResponseEntity.ok().body(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoModel> saveAluno(@RequestBody AlunoRecordDto alunoRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.saveAluno(alunoRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoModel> updateAluno(@PathVariable UUID id, @RequestBody AlunoRecordDto alunoRecordDto) {
        AlunoModel updatedAluno = alunoService.updateAluno(id, alunoRecordDto);
        return ResponseEntity.ok().body(updatedAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable UUID id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso.");
    }
}
