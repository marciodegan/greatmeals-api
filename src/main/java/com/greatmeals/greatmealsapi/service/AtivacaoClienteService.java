package com.greatmeals.greatmealsapi.service;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import com.greatmeals.greatmealsapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Qualifier("urgente")
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro foi ativado!");

    }

}
