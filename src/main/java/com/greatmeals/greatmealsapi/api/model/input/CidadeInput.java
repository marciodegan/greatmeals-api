package com.greatmeals.greatmealsapi.api.model.input;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CidadeInput {

    @ApiModelProperty(example = "Fortaleza", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInput estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoIdInput getEstado() {
        return estado;
    }

    public void setEstado(EstadoIdInput estado) {
        this.estado = estado;
    }
}
