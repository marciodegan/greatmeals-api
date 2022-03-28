package com.greatmeals.greatmealsapi.domain.listeners;

import com.greatmeals.greatmealsapi.domain.event.PedidoCanceladoEvent;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class NotificacaoPedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void aoCancelarPedido(PedidoCanceladoEvent event) {

        Pedido pedido = event.getPedido();

        Set<String> destinatarios = new HashSet<>();
        destinatarios.add(pedido.getCliente().getEmail());

        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("pedido", pedido);

        var mensagem = new EnvioEmailService.Mensagem(destinatarios,
                pedido.getRestaurante().getNome()
                        + " - Pedido Cancelado",
                "pedido-cancelado.html", variaveis);

        envioEmailService.enviar(mensagem);
    }

}
