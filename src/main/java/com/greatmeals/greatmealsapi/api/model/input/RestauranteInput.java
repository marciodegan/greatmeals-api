package com.greatmeals.greatmealsapi.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class RestauranteInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid @NotNull
    private CozinhaIdInput cozinha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public CozinhaIdInput getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaIdInput cozinha) {
        this.cozinha = cozinha;
    }
}
