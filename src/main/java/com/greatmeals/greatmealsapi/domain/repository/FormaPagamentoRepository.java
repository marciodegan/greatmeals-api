package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> todas();
    FormaPagamento porId(Long id);
    FormaPagamento adicionar(FormaPagamento formaPagamento);
    void remover(Long id);
}