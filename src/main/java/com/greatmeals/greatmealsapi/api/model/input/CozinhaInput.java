package com.greatmeals.greatmealsapi.api.model.input;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CozinhaInput {

    @ApiModelProperty(example = "Tailandesa", required = true)
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
