package com.gym.crud.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_FICHA_TREINO")
public class FichaTreinoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;  // Exemplo: "Supino:3x10;Agachamento:4x8"

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "fichasTreino", fetch = FetchType.LAZY)
    private Set<AlunoModel> alunos = new HashSet<>();

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<AlunoModel> alunos) {
        this.alunos = alunos;
    }

    // Manipulação da String de Exercícios
    public List<String> getExercicios() {
        if (descricao != null && !descricao.isEmpty()) {
            return Arrays.asList(descricao.split(";"));
        }
        return List.of(); // Retorna uma lista vazia se a descrição for nula ou vazia
    }

    public void setExercicios(List<String> exercicios) {
        if (exercicios != null && !exercicios.isEmpty()) {
            this.descricao = String.join(";", exercicios);
        } else {
            this.descricao = "";
        }
    }
}
