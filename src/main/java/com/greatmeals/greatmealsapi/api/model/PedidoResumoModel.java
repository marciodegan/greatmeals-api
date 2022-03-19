package com.greatmeals.greatmealsapi.api.model;

import com.greatmeals.greatmealsapi.domain.model.Status;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PedidoResumoModel {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private UsuarioModel cliente;
    private RestauranteSimplificadoModel restaurante;
    private Status status = Status.CRIADO;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UsuarioModel getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioModel cliente) {
        this.cliente = cliente;
    }

    public RestauranteSimplificadoModel getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteSimplificadoModel restaurante) {
        this.restaurante = restaurante;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

