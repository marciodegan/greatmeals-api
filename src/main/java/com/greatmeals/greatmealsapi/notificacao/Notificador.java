package com.greatmeals.greatmealsapi.notificacao;

import com.greatmeals.greatmealsapi.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
