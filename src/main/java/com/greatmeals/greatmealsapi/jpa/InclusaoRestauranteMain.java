package com.greatmeals.greatmealsapi.jpa;

import com.greatmeals.greatmealsapi.GreatmealsApiApplication;
import com.greatmeals.greatmealsapi.domain.model.Restaurante;
import com.greatmeals.greatmealsapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoRestauranteMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GreatmealsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Restaurante Tailandes");

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Japonesa");

        restaurante1 = restaurantes.adicionar(restaurante1);
        restaurante2 = restaurantes.adicionar(restaurante2);

        System.out.printf("%s - %d\n", restaurante1.getNome(), restaurante1.getId());
        System.out.printf("%s - %d\n", restaurante2.getNome(), restaurante2.getId());
    }
}
