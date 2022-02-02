package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findQualquerCoisaByNome(String nome);
//    List<Cozinha> findTodasByNome(String nome);

}
