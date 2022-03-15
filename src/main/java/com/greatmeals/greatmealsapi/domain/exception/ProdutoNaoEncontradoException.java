package com.greatmeals.greatmealsapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long produtoId, Long restauranteId) {
        this(String.format("Produto não foi encontrado com o número %d no restaurante %d", produtoId, restauranteId));
    }

}
