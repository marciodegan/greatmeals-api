package com.greatmeals.greatmealsapi.infrastructure.service;

import com.greatmeals.greatmealsapi.domain.filter.VendaDiariaFilter;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.model.Status;
import com.greatmeals.greatmealsapi.domain.model.dto.VendaDiaria;
import com.greatmeals.greatmealsapi.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class); // Venda Diaria é esperado qdo fizer a query.
        var root = query.from(Pedido.class); // root corresponde a entidade que estamos usando na cláusula from.

        var predicates = new ArrayList<Predicate>();

        var functionConvertTzDataCriacao = builder.function(
                "convert_tz", Date.class, root.get("dataCriacao"),
                builder.literal("+00:00"), builder.literal(timeOffset));
        System.out.println(functionConvertTzDataCriacao);

        var functionDateDataCriacao = builder.function(
                "date", LocalDate.class, functionConvertTzDataCriacao);
        System.out.println(functionDateDataCriacao);
        // Constrói um VendaDiaria pelo seu construtor.
        var selection = builder.construct(VendaDiaria.class, // para cada linha, construir uma VendaDiaria
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }

        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }

        predicates.add(root.get("status").in(Status.CONFIRMADO, Status.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}



/*
*
Reference for SQL Queries:

    select date(p.data_criacao) as data_criacao,
    count(p.id) as total_vendas,
    sum(p.valor_total) as total_faturado
    from pedido p group by date(p.data_criacao)

    select convert_tz('2019-11-03 02:00:30', '+00:00', '-03:00')

    select date(convert_tz('2019-11-03 02:00:30', '+00:00', '-03:00')) as data_criacao,
    count(p.id) as total_vendas,
    sum(p.valor_total) as total_faturado
    from pedido p
    where p.status in ('CONFIRMADO', 'ENTREGUE')
    group by date(convert_tz('2019-11-03 02:00:30', '+00:00', '-03:00'))

* */
