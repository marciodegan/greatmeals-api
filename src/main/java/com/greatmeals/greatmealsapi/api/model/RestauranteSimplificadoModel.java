package com.greatmeals.greatmealsapi.api.model;

public class RestauranteSimplificadoModel {

    private String nome;
    private EnderecoModel endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}
