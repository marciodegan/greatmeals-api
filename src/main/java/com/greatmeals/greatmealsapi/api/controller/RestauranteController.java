package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.model.CozinhaModel;
import com.greatmeals.greatmealsapi.api.model.RestauranteModel;
import com.greatmeals.greatmealsapi.api.model.input.RestauranteInput;
import com.greatmeals.greatmealsapi.domain.exception.CozinhaNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.repository.RestauranteRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public List<RestauranteModel> listar() {
        return toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        return toModel(restauranteService.buscarOuFalhar(restauranteId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = toDomainObject(restauranteInput);

            return toModel(restauranteService.salvar(restaurante));
        } catch (
                CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = toDomainObject(restauranteInput);

            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        restauranteService.excluir(restauranteId);
    }


    private RestauranteModel toModel(Restaurante restaurante) {
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(cozinhaModel);
        return restauranteModel;
    }

    private List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

    private Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }


//    @PatchMapping("/{restauranteId}")
//    public Restaurante atualizarParcial(@PathVariable Long restauranteId,
//                                        @RequestBody Map<String, Object> campos,
//                                        HttpServletRequest request) {
//        Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
//
//        merge(campos, restauranteAtual, request);
//        validate(restauranteAtual, "restaurante");
//
//        return atualizar(restauranteId, restauranteAtual);
//    }
//
//    private void validate(Restaurante restaurante, String objectName){
//        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
//        validator.validate(restaurante, bindingResult);
//        if (bindingResult.hasErrors()) {
//            throw new ValidationException(String.valueOf(bindingResult));
//        }
//    }
//
//    private void merge(Map<String, Object> dadosOrigem,
//                       Restaurante restauranteDestino,
//                       HttpServletRequest request) {
//
//        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
//
//        try {
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//
//            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
//
//            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//                field.setAccessible(true);
//
//                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
//                ReflectionUtils.setField(field, restauranteDestino, novoValor);
//            });
//        } catch (IllegalArgumentException e) {
//            // capturo o IllegalArgumentException
//            Throwable rootCause = ExceptionUtils.getRootCause(e);
//            // e relanço como HttpMessageNotReadableException
//            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
//        }
//    }


}
