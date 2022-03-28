package com.greatmeals.greatmealsapi.domain.event;

import com.greatmeals.greatmealsapi.domain.model.Pedido;

public class PedidoConfirmadoEvent {

    private Pedido pedido;

    public PedidoConfirmadoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
