package com.greatmeals.greatmealsapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format("Cozinha não foi encontrada com o número %d", cozinhaId));
    }
}
