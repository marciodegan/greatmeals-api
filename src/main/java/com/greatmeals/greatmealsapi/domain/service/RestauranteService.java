package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.model.FormaPagamento;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.repository.CozinhaRepository;
import com.greatmeals.greatmealsapi.domain.repository.FormaPagamentoRepository;
import com.greatmeals.greatmealsapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
        Long formaPagamentoId = restaurante.getFormaPagamento().getId();
        FormaPagamento formaPagamento = formaPagamentoRepository.porId(formaPagamentoId);

        if(cozinha == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cozinha com o codigo %d", cozinhaId));
        }
        if(formaPagamento == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe forma de pagamento com o codigo %d", formaPagamentoId));
        }
        restaurante.setCozinha(cozinha);
        restaurante.setFormaPagamento(formaPagamento);

        return restauranteRepository.adicionar(restaurante);
    }

    public List<Restaurante> listar() {
        return restauranteRepository.todos();
    };

    public Restaurante porId(Long id) {
        return restauranteRepository.porId(id);
    }

    public void remover(Long id) {
        try {
            restauranteRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante não foi encontrado com o número" + id));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Restaurante de código %d não pode ser removido, pois já está em uso", id));
        }
    }
}