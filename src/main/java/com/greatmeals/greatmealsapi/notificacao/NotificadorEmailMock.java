package com.greatmeals.greatmealsapi.notificacao;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmailMock implements Notificador {

    public NotificadorEmailMock() {
        System.out.println("NotificadorEmail MOCK");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("MOCK: Notificando %s através do e-mail %s: %s\n ", cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
