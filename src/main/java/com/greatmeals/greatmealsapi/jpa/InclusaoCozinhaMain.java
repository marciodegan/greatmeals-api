package com.greatmeals.greatmealsapi.jpa;

import com.greatmeals.greatmealsapi.GreatmealsApiApplication;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(GreatmealsApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Japonesa");

        cozinha1 = cozinhas.adicionar(cozinha1);
        cozinha2 = cozinhas.adicionar(cozinha2);

        System.out.printf("%s - %d\n", cozinha1.getNome(), cozinha1.getId());
        System.out.printf("%s - %d\n", cozinha2.getNome(), cozinha2.getId());
    }
}
