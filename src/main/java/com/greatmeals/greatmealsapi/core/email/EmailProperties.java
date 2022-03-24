package com.greatmeals.greatmealsapi.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated // para o @NotNull funcionar
@ConfigurationProperties("greatmeals.email")
@Component
public class EmailProperties {

    @NotNull // se esquercermos dessa propriedade, a aplicação nem sobe.
    private String remetente;

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
}
