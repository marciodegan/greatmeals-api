package com.greatmeals.greatmealsapi.domain.exception;

public class PermissaoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PermissaoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradoException(Long permissaoId) {
        this(String.format("Permissao não foi encontrada com o número %d", permissaoId));
    }
}