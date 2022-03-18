package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.PedidoInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.PedidoModelAssembler;
import com.greatmeals.greatmealsapi.api.assembler.PedidoResumoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.PedidoResumoModel;
import com.greatmeals.greatmealsapi.api.model.input.PedidoInput;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.model.Usuario;
import com.greatmeals.greatmealsapi.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> pedidosTodos = pedidoService.listarTodos();

        return pedidoResumoModelAssembler.toCollectionModel(pedidosTodos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoResumoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    public PedidoResumoModel emitir(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(1L);
        pedidoService.emitir(pedido);
        return pedidoResumoModelAssembler.toModel(pedido);
    }
}
