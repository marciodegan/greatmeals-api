package com.greatmeals.greatmealsapi.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class VendaDiaria {

    private Date data;
    private Long totalVendas;
    private BigDecimal totalFaturado;

    public VendaDiaria(Date data, Long totalVendas, BigDecimal totalFaturado) {
        this.data = data;
        this.totalVendas = totalVendas;
        this.totalFaturado = totalFaturado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Long totalVendas) {
        this.totalVendas = totalVendas;
    }

    public BigDecimal getTotalFaturado() {
        return totalFaturado;
    }

    public void setTotalFaturado(BigDecimal totalFaturado) {
        this.totalFaturado = totalFaturado;
    }
}
