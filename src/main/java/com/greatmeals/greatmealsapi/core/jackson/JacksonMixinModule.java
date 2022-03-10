package com.greatmeals.greatmealsapi.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.greatmeals.greatmealsapi.api.model.mixin.CidadeMixin;
import com.greatmeals.greatmealsapi.api.model.mixin.CozinhaMixin;
import com.greatmeals.greatmealsapi.domain.model.Cidade;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
