package com.greatmeals.greatmealsapi.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/*
* Essa classe customiza uma instancia de LocalValidatorFactoryBean.
* Indicamos que o ValidationMessageSource é um MessageSource
* Se não especificamos ele vai usar o ValidationMessages.properties.
* Mas assim, especificamos e ela vai usar o Messages.properties na hora que o Bean Validation vai resolver as suas mensagens.
* */

@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
