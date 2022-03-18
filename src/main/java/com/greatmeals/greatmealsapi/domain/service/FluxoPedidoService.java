package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(Status.CRIADO)) {
            throw new NegocioException(
                    String.format("Status do pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(), pedido.getStatus().getDescricao(), Status.CONFIRMADO.getDescricao()));
        }
        pedido.setStatus(Status.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(Status.CONFIRMADO)) {
            throw new NegocioException(
                    String.format("Status do pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(), pedido.getStatus().getDescricao(), Status.ENTREGUE.getDescricao()));
        }
        pedido.setStatus(Status.ENTREGUE);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(pedidoId);
        if (!pedido.getStatus().equals(Status.CRIADO)) {
            throw new NegocioException(
                    String.format("Status do pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(), pedido.getStatus().getDescricao(), Status.CANCELADO.getDescricao()));
        }
        pedido.setStatus(Status.CANCELADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }
}
