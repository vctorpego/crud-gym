package com.gym.crud.repositories;

import com.gym.crud.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlunoRepository extends JpaRepository<AlunoModel, UUID> {
}
