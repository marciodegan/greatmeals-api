package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class PedidoFluxoController {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable Long pedidoId) {
        fluxoPedidoService.confirmar(pedidoId);
    }
}