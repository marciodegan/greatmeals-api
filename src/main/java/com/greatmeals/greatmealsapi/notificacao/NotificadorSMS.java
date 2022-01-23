package com.greatmeals.greatmealsapi.notificacao;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import org.springframework.stereotype.Component;


@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do SMS através do telefone %s: %s\n ", cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}