package com.greatmeals.greatmealsapi.api.model.input;

import javax.validation.constraints.NotBlank;

public class UsuarioComSenhaInput extends UsuarioInput {

    @NotBlank
    private String senha;

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
