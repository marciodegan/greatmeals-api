package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

//    List<Permissao> todas();
////    Permissao porId(Long id);
////    Permissao adicionar(Permissao permissao);
////    void remover(Permissao permissao);
}