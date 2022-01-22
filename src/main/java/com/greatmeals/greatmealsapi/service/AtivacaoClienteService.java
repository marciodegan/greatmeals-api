package com.greatmeals.greatmealsapi.service;

import com.greatmeals.greatmealsapi.modelo.Cliente;
import com.greatmeals.greatmealsapi.notificacao.Notificador;

public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteService" + notificador);
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro foi ativado!");
    }
}
