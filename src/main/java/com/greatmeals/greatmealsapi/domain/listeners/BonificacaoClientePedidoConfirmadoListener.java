package com.greatmeals.greatmealsapi.domain.listeners;

import com.greatmeals.greatmealsapi.domain.event.PedidoConfirmadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BonificacaoClientePedidoConfirmadoListener {

    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

        System.out.println("Logica de calculo de bonificacao..." + event.getPedido().getCliente().getNome());

    }

}
