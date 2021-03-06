package com.greatmeals.greatmealsapi.domain.repository;

import com.greatmeals.greatmealsapi.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
        JpaSpecificationExecutor<Pedido> {

//    @Query("from Pedido where codigo = :codigo")
    Optional<Pedido> findByCodigo(String codigo);

//    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    Page<Pedido> findAll(Pageable pageable);
}