package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.exception.PedidoNaoEncontradoException;
import com.greatmeals.greatmealsapi.domain.model.*;
import com.greatmeals.greatmealsapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private CadastroProdutoService produtoService;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        validar(pedido);
        validarItens(pedido);
        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.setEndereco(pedido.getEndereco());
        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private void validar(Pedido pedido) {

        Restaurante restaurante = restauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
        Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEndereco().getCidade().getId());
        Usuario cliente = usuarioService.buscarOuFalhar(pedido.getCliente().getId());

        pedido.setCliente(cliente);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setEndereco(pedido.getEndereco());
        pedido.setRestaurante(restaurante);

        if(!restaurante.getAtivo() || !restaurante.getAberto()){
            throw new NegocioException(String.format("Restaurante '%s' não está aberto ou ativo no momento",
                    restaurante.getNome()));
        }

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante",
                    formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.buscarOuFalhar(
                    pedido.getRestaurante().getId(), item.getProduto().getId());
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }
}

