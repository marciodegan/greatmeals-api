package com.greatmeals.greatmealsapi.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE }) // Type -> só pode ser usado em uma classe, uma interface. Não pode ser usada em uma propriedade.
@Retention(RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluiDescricao {

    String message() default "descrição obrigatória inválida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String valorField();
    String descricaoField();
    String descricaoObrigatoria();
}
