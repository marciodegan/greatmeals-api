package com.greatmeals.greatmealsapi.api.model.input;

import javax.validation.constraints.NotBlank;

public class FormaPagamentoInput {

    @NotBlank
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
