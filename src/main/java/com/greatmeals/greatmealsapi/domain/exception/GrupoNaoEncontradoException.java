package com.greatmeals.greatmealsapi.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this(String.format("Grupo não foi encontrado com o número %d", grupoId));
    }
}
