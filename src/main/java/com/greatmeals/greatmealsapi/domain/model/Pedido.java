package com.greatmeals.greatmealsapi.domain.model;

import com.greatmeals.greatmealsapi.domain.event.PedidoCanceladoEvent;
import com.greatmeals.greatmealsapi.domain.event.PedidoConfirmadoEvent;
import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.service.EnvioEmailService;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(nullable = false)
    private OffsetDateTime dataConfirmacao = OffsetDateTime.now();

    @Column(nullable = false)
    private OffsetDateTime dataEntrega = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Status status = Status.CRIADO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) // ao salvar o pedido, vai salvar os itens do pedido.
    private List<ItemPedido> itens = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OffsetDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(OffsetDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public OffsetDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(OffsetDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Status getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.subtotal = getItens().stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(this.taxaFrete);


        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void confirmar() {
        setStatus(Status.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());

        registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void entregar() {
        setStatus(Status.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    public void cancelar() {
        setStatus(Status.CANCELADO);
        setDataEntrega(OffsetDateTime.now()); // necessario criar dataCancelamento

        registerEvent(new PedidoCanceladoEvent(this));
    }

    private void setStatus(Status novoStatus) {
       if (getStatus().naoPodeAlterarPara(novoStatus)){
           throw new NegocioException(
                   String.format("Status do pedido %s não pode ser alterado de %s para %s",
                           getCodigo(), getStatus().getDescricao(), novoStatus.getDescricao()));
       }
       this.status = novoStatus;
    }

    @PrePersist // método de callback do JPA. Antes de persistir um novo registro de pedido, executa esse método.
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }
}
