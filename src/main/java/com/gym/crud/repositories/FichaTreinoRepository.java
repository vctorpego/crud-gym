package com.gym.crud.repositories;

import com.gym.crud.models.FichaTreinoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FichaTreinoRepository extends JpaRepository<FichaTreinoModel, UUID> {
}
