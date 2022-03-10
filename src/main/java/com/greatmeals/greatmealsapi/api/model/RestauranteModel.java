package com.greatmeals.greatmealsapi.api.model;

import java.math.BigDecimal;

public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal precoFrete;
    private CozinhaModel cozinha;

    private String cozinhaNome;
    private Long cozinhaId;

    public String getCozinhaNome() {
        return cozinhaNome;
    }

    public void setCozinhaNome(String cozinhaNome) {
        this.cozinhaNome = cozinhaNome;
    }

    public Long getCozinhaId() {
        return cozinhaId;
    }

    public void setCozinhaId(Long cozinhaId) {
        this.cozinhaId = cozinhaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoFrete() {
        return precoFrete;
    }

    public void setPrecoFrete(BigDecimal precoFrete) {
        this.precoFrete = precoFrete;
    }

    public CozinhaModel getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaModel cozinha) {
        this.cozinha = cozinha;
    }

//    public Restaurante toModel(RestauranteModel restauranteModel) {
//        return new Restaurante(restauranteModel.getNome(), restauranteModel.getTaxaFrete(), restauranteModel.getCozinha().getId());
//    }

}
