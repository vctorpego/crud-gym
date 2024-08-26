package com.gym.crud.controllers;

import com.gym.crud.dtos.FichaTreinoRecordDto;
import com.gym.crud.models.FichaTreinoModel;
import com.gym.crud.services.FichaTreinoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gym/fichas-treino")
public class FichaTreinoController {

    private final FichaTreinoService fichaTreinoService;

    public FichaTreinoController(FichaTreinoService fichaTreinoService) {
        this.fichaTreinoService = fichaTreinoService;
    }

    @GetMapping
    public ResponseEntity<List<FichaTreinoModel>> getAllFichasTreino() {
        return ResponseEntity.status(HttpStatus.OK).body(fichaTreinoService.getAllFichasTreino());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaTreinoModel> findFichaTreinoById(@PathVariable UUID id) {
        FichaTreinoModel fichaTreino = fichaTreinoService.findFichaTreinoById(id);
        return ResponseEntity.ok().body(fichaTreino);
    }

    @PostMapping
    public ResponseEntity<FichaTreinoModel> saveFichaTreino(@RequestBody FichaTreinoRecordDto fichaTreinoRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaTreinoService.saveFichaTreino(fichaTreinoRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FichaTreinoModel> updateFichaTreino(@PathVariable UUID id, @RequestBody FichaTreinoRecordDto fichaTreinoRecordDto) {
        FichaTreinoModel updatedFichaTreino = fichaTreinoService.updateFichaTreino(id, fichaTreinoRecordDto);
        return ResponseEntity.ok().body(updatedFichaTreino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFichaTreino(@PathVariable UUID id) {
        fichaTreinoService.deleteFichaTreino(id);
        return ResponseEntity.status(HttpStatus.OK).body("Ficha de treino deletada com sucesso.");
    }
}
