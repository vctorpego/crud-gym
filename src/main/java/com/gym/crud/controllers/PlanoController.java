package com.gym.crud.controllers;

import com.gym.crud.dtos.PlanoRecordDto;
import com.gym.crud.models.PlanoModel;
import com.gym.crud.services.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gym/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping
    public ResponseEntity<List<PlanoModel>> getAllPlanos() {
        return ResponseEntity.status(HttpStatus.OK).body(planoService.getAllPlanos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoModel> findPlanoById(@PathVariable UUID id) {
        PlanoModel plano = planoService.findPlanoById(id);
        return ResponseEntity.ok().body(plano);
    }

    @PostMapping
    public ResponseEntity<PlanoModel> savePlano(@RequestBody PlanoRecordDto planoRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoService.savePlano(planoRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoModel> updatePlano(@PathVariable UUID id, @RequestBody PlanoRecordDto planoRecordDto) {
        PlanoModel updatedPlano = planoService.updatePlano(id, planoRecordDto);
        return ResponseEntity.ok().body(updatedPlano);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlano(@PathVariable UUID id) {
        planoService.deletePlano(id);
        return ResponseEntity.status(HttpStatus.OK).body("Plano deletado com sucesso.");
    }
}
