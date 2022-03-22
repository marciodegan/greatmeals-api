package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.FotoProduto;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

}
