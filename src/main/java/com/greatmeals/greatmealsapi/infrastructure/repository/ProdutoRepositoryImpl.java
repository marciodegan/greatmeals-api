package com.greatmeals.greatmealsapi.infrastructure.repository;

import com.greatmeals.greatmealsapi.domain.model.FotoProduto;
import com.greatmeals.greatmealsapi.domain.repository.ProdutoRepository;
import com.greatmeals.greatmealsapi.domain.repository.ProdutoRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    @Lazy
    private ProdutoRepository produtoRepository;

    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }
}