package com.greatmeals.greatmealsapi.api.model;

import javax.validation.constraints.NotBlank;

public class PermissaoModel {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
}
