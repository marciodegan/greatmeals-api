package com.greatmeals.greatmealsapi.core.squilly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
/*
     Método que retorna uma instância FilterRegistrationBean  tipado como tipo do filtro que queremos (SquigglyRequestFilter)
     Adiciona um filtro nas requisições. Sempre que uma requisição chegar na API, vai passar por esse filtro.
     Exemplos de pesquisa na requisição:  fields ->   codigo,valorTotal,sub*,cliente.id
                                                      ou cliente[id,nome] -> para isso incluimos a classe TomcatCustomizer.class
                                                      ou cliente[-id] -> menos o id.
                                                      ou -codigo,-restaurante
* */

@Configuration
public class SquillyConfig {

    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squillyRequestFilter(ObjectMapper objectMapper) {
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider("campos", null));

        // define quais urls devem usar o Squilly
        var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");

        var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>();
        filterRegistration.setFilter(new SquigglyRequestFilter());
        filterRegistration.setUrlPatterns(urlPatterns);
        filterRegistration.setOrder(1);

        return filterRegistration;
    }
}
