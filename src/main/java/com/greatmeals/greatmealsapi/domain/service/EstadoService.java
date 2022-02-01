package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.model.Estado;
import com.greatmeals.greatmealsapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.adicionar(estado);
    }

    public List<Estado> listar() {
        return estadoRepository.todos();
    };

    public Estado porId(Long id) {
        return estadoRepository.porId(id);
    }

    public void remover(Long id) {
        try {
            estadoRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Estado não foi encontrado com o número" + id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois já está em uso", id));
        }
    }
}