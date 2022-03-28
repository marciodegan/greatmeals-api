package com.greatmeals.greatmealsapi.domain.event;

import com.greatmeals.greatmealsapi.domain.model.Pedido;

public class PedidoCanceladoEvent {

    private Pedido pedido;

    public PedidoCanceladoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
