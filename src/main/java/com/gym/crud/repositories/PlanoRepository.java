package com.gym.crud.repositories;

import com.gym.crud.models.PlanoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoRepository extends JpaRepository<PlanoModel, UUID> {
}
