package com.greatmeals.greatmealsapi.domain.model;

import com.greatmeals.greatmealsapi.core.validation.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    @NotNull(groups = Groups.EstadoId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
