package com.greatmeals.greatmealsapi.service;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import com.greatmeals.greatmealsapi.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @Autowired(required = false)
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        if (notificador != null) {
            notificador.notificar(cliente, "Seu cadastro foi ativado!");
        } else {
            System.out.println("Nao existe notificador mas cliente foi ativado");
        }
    }
}
