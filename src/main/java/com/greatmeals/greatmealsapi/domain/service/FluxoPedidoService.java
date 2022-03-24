package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();

        Set<String> destinatarios = new HashSet<>();
        destinatarios.add(pedido.getCliente().getEmail());

        var mensagem = new EnvioEmailService.Mensagem(destinatarios,
                pedido.getRestaurante().getNome()
                        + " - Pedido Confirmado",
                "Corpo do Email");

        envioEmailService.enviar(mensagem);
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
    }
}
