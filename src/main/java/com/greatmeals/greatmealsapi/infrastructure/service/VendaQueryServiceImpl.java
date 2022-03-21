package com.greatmeals.greatmealsapi.infrastructure.service;

import com.greatmeals.greatmealsapi.domain.filter.VendaDiariaFilter;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.model.dto.VendaDiaria;
import com.greatmeals.greatmealsapi.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class); // Venda Diaria é esperado qdo fizer a query.
        var root = query.from(Pedido.class); // root corresponde a entidade que estamos usando na cláusula from.

        var functionDateDataCriacao = builder.function(
                "date", LocalDate.class, root.get("dataCriacao"));
        // Constrói um VendaDiaria pelo seu construtor.
        var selection = builder.construct(VendaDiaria.class, // para cada linha, construir uma VendaDiaria
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));
        query.select(selection);
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
