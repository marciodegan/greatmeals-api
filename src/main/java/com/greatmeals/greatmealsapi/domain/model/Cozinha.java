package com.greatmeals.greatmealsapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greatmeals.greatmealsapi.core.validation.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "cozinha")
public class Cozinha {

//    @NotNull(groups = Groups.CozinhaId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cozinha cozinha = (Cozinha) o;

        return Objects.equals(id, cozinha.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }
}
