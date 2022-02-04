package com.greatmeals.greatmealsapi.infrastructure.repository.spec;

import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

//    private static final long serialVersionUID = 1L;

    private String nome;

    public RestauranteComNomeSemelhanteSpec(String nome) {
        this.nome = nome;
    }

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        return builder.like(root.get("nome"), "%" + nome + "%");
    }
}
