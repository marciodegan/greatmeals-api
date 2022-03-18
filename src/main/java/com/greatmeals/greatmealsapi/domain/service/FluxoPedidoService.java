package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        pedido.cancelar();
    }
}
