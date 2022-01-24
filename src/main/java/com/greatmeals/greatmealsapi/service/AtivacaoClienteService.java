package com.greatmeals.greatmealsapi.service;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import com.greatmeals.greatmealsapi.notificacao.NivelUrgencia;
import com.greatmeals.greatmealsapi.notificacao.Notificador;
import com.greatmeals.greatmealsapi.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @PostConstruct
    public void init() {
        System.out.println("INIT");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy");
    }
    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro foi ativado!");

    }

}
