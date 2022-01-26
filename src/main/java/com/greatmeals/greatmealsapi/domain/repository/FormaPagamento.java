package com.greatmeals.greatmealsapi.domain.repository;

import java.util.List;

public interface FormaPagamento {

    List<FormaPagamento> todas();
    FormaPagamento porId(Long id);
    FormaPagamento adicionar(FormaPagamento formaPagamento);
    void remover(FormaPagamento formaPagamento);
}