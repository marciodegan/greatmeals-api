package com.greatmeals.greatmealsapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.greatmeals.greatmealsapi.api.model.mixin.RestauranteMixin;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
