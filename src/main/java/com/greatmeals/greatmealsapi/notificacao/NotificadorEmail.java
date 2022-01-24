package com.greatmeals.greatmealsapi.notificacao;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("Notificador email REAL");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s\n ", cliente.getNome(), cliente.getEmail(), mensagem);
    }


}
