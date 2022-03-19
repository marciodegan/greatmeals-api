package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.ProdutoInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.ProdutoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.ProdutoModel;
import com.greatmeals.greatmealsapi.api.model.input.ProdutoInput;
import com.greatmeals.greatmealsapi.domain.model.Produto;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.service.CadastroProdutoService;
import com.greatmeals.greatmealsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private CadastroProdutoService produtoService;

    @GetMapping
    public List<ProdutoModel> listar(@PathVariable Long restauranteId,
                                     @RequestParam(required = false) boolean incluirInativos) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = null;

        if (incluirInativos) {
            todosProdutos = produtoService.listarTodosByRestaurante(restaurante);
        } else {
            todosProdutos = produtoService.listarTodosAtivosByRestaurante(restaurante);
        }
        return produtoModelAssembler.toCollectionModel(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long produtoId, @PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        return produtoModelAssembler.toModel(produtoService.buscarOuFalhar(restauranteId, produtoId));
    }

    @PostMapping
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        return produtoModelAssembler.toModel(produtoService.salvar(produto));
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId,
                                  @PathVariable Long produtoId,
                                  @RequestBody ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
        return produtoModelAssembler.toModel(produtoService.salvar(produto));
    }
}