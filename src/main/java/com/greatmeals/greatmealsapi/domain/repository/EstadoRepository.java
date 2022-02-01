package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> todos();
    Estado porId(Long id);
    Estado adicionar(Estado estado);
    void remover(Long id);
}