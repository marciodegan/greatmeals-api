package com.greatmeals.greatmealsapi.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FotoProdutoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradaException(Long produtoId, Long restauranteId) {
        this(String.format("Foto não foi encontrada para o produto %d e restaurante %d", produtoId, restauranteId));
    }
}
