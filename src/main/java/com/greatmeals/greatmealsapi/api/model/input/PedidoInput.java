package com.greatmeals.greatmealsapi.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PedidoInput {

    @Valid
    private RestauranteIdInput restaurante;
    @Valid
    private FormaPagamentoIdInput formaPagamento;
    @Valid
    private EnderecoInput endereco;
    @Valid @NotNull @Size(min=1)
    private List<ItemPedidoInput> itens;

    public RestauranteIdInput getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteIdInput restaurante) {
        this.restaurante = restaurante;
    }

    public FormaPagamentoIdInput getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoIdInput formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }

    public List<ItemPedidoInput> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoInput> itens) {
        this.itens = itens;
    }
}
