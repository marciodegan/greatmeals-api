package com.greatmeals.greatmealsapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PedidoNaoEncontradoException(String codigoPedido) {
        super(String.format("Pedido não foi encontrado com o número %s", codigoPedido));
    }
}
