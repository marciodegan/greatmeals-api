package com.greatmeals.greatmealsapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.repository.RestauranteRepository;
import com.greatmeals.greatmealsapi.domain.service.RestauranteService;
import com.greatmeals.greatmealsapi.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.greatmeals.greatmealsapi.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/por-nome-e-frete")
    public List<Restaurante> restaurantePorNomeEFrete(String nome,
                                                      BigDecimal taxaFreteInicial,
                                                      BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/com-frete-gratis")
    public List<Restaurante> restauranteComFreteGraris(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
        return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
    }

    @GetMapping("/por-taxa-frete")
    public List<Restaurante> restaurantePorTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/por-nome-e-cozinha")
    public List<Restaurante> restaurantePorNomeECozinha(
            String nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("/top-2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurante-exists")
    public boolean restaurantesExists(String nome) {
        return restauranteRepository.existsByNomeContaining(nome);
    }

    @GetMapping("/restaurante-count-cozinha")
    public int restaurantesCountCozinha(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable("restauranteId") Long id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = restauranteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        try {
            Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(id);

            if(restauranteEncontrado.isPresent()) {
                BeanUtils.copyProperties(restaurante, restauranteEncontrado.get(), "id");
                return ResponseEntity.ok(restauranteService.salvar(restauranteEncontrado.get()));
            }
            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            restauranteService.remover(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {

        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        if(restauranteAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual.get());
        return atualizar(restauranteId, restauranteAtual.get());
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//            System.out.println(nomePropriedade + " = " + valorPropriedade);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
