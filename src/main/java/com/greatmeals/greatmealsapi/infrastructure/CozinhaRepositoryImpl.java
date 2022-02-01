package com.greatmeals.greatmealsapi.infrastructure;

import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> todas() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nome) {
        return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    @Override
    public Cozinha porId(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }


    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = porId(id);
        if(cozinha == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }
}
