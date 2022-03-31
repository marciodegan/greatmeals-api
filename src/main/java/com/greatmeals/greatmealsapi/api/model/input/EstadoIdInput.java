package com.greatmeals.greatmealsapi.api.model.input;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

