package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.filter.VendaDiariaFilter;
import com.greatmeals.greatmealsapi.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);
}
