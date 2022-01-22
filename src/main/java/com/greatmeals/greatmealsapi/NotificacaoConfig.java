package com.greatmeals.greatmealsapi;

import com.greatmeals.greatmealsapi.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp.greatmeals.com.br");
        notificador.setCaixaAlta(true);

        return notificador;
    }
}
