package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.exception.PedidoNaoEncontradoException;
import com.greatmeals.greatmealsapi.domain.model.*;
import com.greatmeals.greatmealsapi.domain.repository.PedidoRepository;
import com.greatmeals.greatmealsapi.domain.repository.filter.PedidoFilter;
import com.greatmeals.greatmealsapi.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroProdutoService produtoService;

    public List<Pedido> pesquisar(PedidoFilter filtro) {
        return pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
    }

    public Pedido buscarOuFalhar(String codigoPedido) {
        return pedidoRepository.findByCodigo(codigoPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }

}

