package com.greatmeals.greatmealsapi.api.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.ImmutableMap;
import com.greatmeals.greatmealsapi.api.assembler.PedidoInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.PedidoModelAssembler;
import com.greatmeals.greatmealsapi.api.assembler.PedidoResumoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.PedidoModel;
import com.greatmeals.greatmealsapi.api.model.PedidoResumoModel;
import com.greatmeals.greatmealsapi.api.model.input.PedidoInput;
import com.greatmeals.greatmealsapi.core.data.PageableTranslator;
import com.greatmeals.greatmealsapi.domain.model.Pedido;
import com.greatmeals.greatmealsapi.domain.model.Usuario;
import com.greatmeals.greatmealsapi.domain.repository.filter.PedidoFilter;
import com.greatmeals.greatmealsapi.domain.service.CadastroPedidoService;
import com.greatmeals.greatmealsapi.domain.service.EmissaoPedidoService;
import com.greatmeals.greatmealsapi.infrastructure.repository.spec.PedidoSpecs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;


    @GetMapping
    public Page<PedidoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = cadastroPedidoService.pesquisar(filtro, pageable);

        List<PedidoModel> pedidosModel = pedidoModelAssembler.toCollectionModel(pedidosPage.getContent());

        Page<PedidoModel> pedidosModelPage = new PageImpl<>(pedidosModel, pageable, pedidosPage.getTotalElements());

        return pedidosModelPage;
    }

    @GetMapping("/{codigoId}")
    public PedidoModel buscar(@PathVariable String codigoId) {
        Pedido pedido = cadastroPedidoService.buscarOuFalhar(codigoId);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    public PedidoResumoModel emitir(@RequestBody @Valid PedidoInput pedidoInput) {
        Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(1L);

        emissaoPedidoService.emitir(pedido);

        return pedidoResumoModelAssembler.toModel(pedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
                "nomeCliente", "cliente.nome",
                "restaurante.nome", "restaurante.nome",
                "valorTotal", "valorTotal"
        );
        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
