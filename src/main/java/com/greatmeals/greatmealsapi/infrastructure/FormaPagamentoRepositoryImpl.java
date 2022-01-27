package com.greatmeals.greatmealsapi.infrastructure;

import com.greatmeals.greatmealsapi.domain.model.FormaPagamento;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> todas() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();
    }

    @Override
    public FormaPagamento porId(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento adicionar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        FormaPagamento formaPagamento = porId(id);
        manager.remove(formaPagamento);
    }
}