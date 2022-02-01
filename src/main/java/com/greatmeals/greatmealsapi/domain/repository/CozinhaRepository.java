package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> todas();
    List<Cozinha> consultarPorNome(String nome);
    Cozinha porId(Long id);
    Cozinha adicionar(Cozinha cozinha);
    void remover(Long id);

}
