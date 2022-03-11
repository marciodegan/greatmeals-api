package com.greatmeals.greatmealsapi.api.model;

import com.greatmeals.greatmealsapi.domain.model.Estado;

public class CidadeModel {

    private String nome;
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
