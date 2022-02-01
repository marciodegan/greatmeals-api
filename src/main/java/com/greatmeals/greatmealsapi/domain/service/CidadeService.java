package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.model.Cidade;
import com.greatmeals.greatmealsapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade) {
        Long cidadeId = cidade.getId();
        Cidade cidadeAtual = cidadeRepository.porId(cidadeId);

        if(cidadeAtual == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cidade com o codigo %d", cidadeAtual));
        }

        cidadeAtual.setNome(cidade.getNome());

        return cidadeRepository.adicionar(cidadeAtual);
    }

    public List<Cidade> listar() {
        return cidadeRepository.todas();
    };

    public Cidade porId(Long id) {
        return cidadeRepository.porId(id);
    }

    public void remover(Long id) {
        try {
            cidadeRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade não foi encontrado com o número" + id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser removido, pois já está em uso", id));
        }
    }
}