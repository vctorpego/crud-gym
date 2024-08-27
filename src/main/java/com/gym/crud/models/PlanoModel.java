package com.gym.crud.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PLANO")
public class PlanoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; //Mudar para long

    @Column(nullable = false, unique = true)
    private String nomePlano;

    @Column(nullable = false)
    private double valor;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "plano", fetch = FetchType.LAZY)
    private Set<AlunoModel> alunos = new HashSet<>();

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Set<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<AlunoModel> alunos) {
        this.alunos = alunos;
    }
}
