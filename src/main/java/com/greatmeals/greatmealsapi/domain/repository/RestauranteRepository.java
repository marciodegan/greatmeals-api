package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> todos();
    Restaurante porId(Long id);
    Restaurante adicionar(Restaurante restaurante);
    void remover(Long id);
}
