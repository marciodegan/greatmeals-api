package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.repository.RestauranteRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listar() { return restauranteRepository.findAll(); }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return restauranteService.buscarOuFalhar(restauranteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        return restauranteService.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId,
                                 @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual,
                        "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        return restauranteService.salvar(restauranteAtual);
    }

    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        restauranteService.excluir(restauranteId);
    }
}
