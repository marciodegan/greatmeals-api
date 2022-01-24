package com.greatmeals.greatmealsapi.listener;

import com.greatmeals.greatmealsapi.notificacao.NivelUrgencia;
import com.greatmeals.greatmealsapi.notificacao.Notificador;
import com.greatmeals.greatmealsapi.notificacao.TipoDoNotificador;
import com.greatmeals.greatmealsapi.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo");
    }
}