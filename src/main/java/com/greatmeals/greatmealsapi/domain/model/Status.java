package com.greatmeals.greatmealsapi.domain.model;

import java.util.Arrays;
import java.util.List;

public enum Status {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String descricao;
    private List<Status> statusAnteriores;

    Status(String descricao, Status... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean naoPodeAlterarPara(Status novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }
}
