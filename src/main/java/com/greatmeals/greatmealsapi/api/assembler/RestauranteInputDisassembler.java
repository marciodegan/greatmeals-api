package com.greatmeals.greatmealsapi.api.assembler;

import com.greatmeals.greatmealsapi.api.model.input.RestauranteInput;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
