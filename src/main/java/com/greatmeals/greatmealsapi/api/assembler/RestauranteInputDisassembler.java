package com.greatmeals.greatmealsapi.api.assembler;

import com.greatmeals.greatmealsapi.api.model.input.RestauranteInput;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {

        return new ModelMapper().map(restauranteInput, Restaurante.class);
    }
}
