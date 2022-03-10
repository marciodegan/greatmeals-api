package com.greatmeals.greatmealsapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.greatmeals.greatmealsapi.domain.model.Estado;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
