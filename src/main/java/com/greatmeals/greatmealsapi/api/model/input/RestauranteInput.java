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

    @Valid @NotNull
    private EnderecoInput endereco;

    private String aberto;

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

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }

    public String getAberto() {
        return aberto;
    }

    public void setAberto(String aberto) {
        this.aberto = aberto;
    }
}
