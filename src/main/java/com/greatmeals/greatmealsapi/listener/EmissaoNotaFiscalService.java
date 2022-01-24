package com.greatmeals.greatmealsapi.listener;

import com.greatmeals.greatmealsapi.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        System.out.println("Emitindo Nota Fiscal para cliente " + event.getCliente().getNome());
    }
}