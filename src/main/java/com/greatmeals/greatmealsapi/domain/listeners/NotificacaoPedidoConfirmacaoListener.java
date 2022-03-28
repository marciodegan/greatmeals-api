package com.greatmeals.greatmealsapi.domain.listeners;

import com.greatmeals.greatmealsapi.domain.event.PedidoConfirmadoEvent;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class NotificacaoPedidoConfirmacaoListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @EventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

        Pedido pedido = event.getPedido();

        Set<String> destinatarios = new HashSet<>();
        destinatarios.add(pedido.getCliente().getEmail());

        Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("pedido", pedido);

        var mensagem = new EnvioEmailService.Mensagem(destinatarios,
                pedido.getRestaurante().getNome()
                        + " - Pedido Confirmado",
                "pedido-confirmado.html", variaveis);

        envioEmailService.enviar(mensagem);
    }

}
