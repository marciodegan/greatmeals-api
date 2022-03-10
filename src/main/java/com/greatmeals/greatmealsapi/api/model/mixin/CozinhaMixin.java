package com.greatmeals.greatmealsapi.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
