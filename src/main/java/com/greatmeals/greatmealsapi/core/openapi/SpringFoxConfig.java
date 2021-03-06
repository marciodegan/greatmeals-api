package com.greatmeals.greatmealsapi.core.openapi;

import com.fasterxml.classmate.TypeResolver;
import com.greatmeals.greatmealsapi.api.exceptionhandler.Problem;
import com.greatmeals.greatmealsapi.api.model.CozinhaModel;
import com.greatmeals.greatmealsapi.api.openapi.model.CozinhasModelOpenApi;
import com.greatmeals.greatmealsapi.api.openapi.model.PageableModelOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {

        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.greatmeals.greatmealsapi.api"))
//                .apis(Predicates.and(
//                        RequestHandlerSelectors.basePackage("com.greatmeals.greatmealsapi.api"),
//                        RequestHandlerSelectors.basePackage("com.greatmeals.greatmealsapi.outropacote")))
//                .paths(PathSelectors.ant("/restaurantes/*"))
//                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalGetResponseMessage())
                .globalResponseMessage(RequestMethod.POST, globalPostAndPutResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, globalPostAndPutResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, CozinhaModel.class),
                        CozinhasModelOpenApi.class))
                .apiInfo(apiInfo())
                .tags(
                        new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Grupos", "Gerencia os grupos de usu??rios"),
                        new Tag("Cozinhas", "Gerencia as cozinhas")
                );
    }

    private List<ResponseMessage> globalGetResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno do servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<ResponseMessage> globalPostAndPutResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("N??o permitido")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
//                new ResponseMessageBuilder()
//                        .code(HttpStatus.FORBIDDEN.value())
//                        .message("N??o permitido")
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code(HttpStatus.UNAUTHORIZED.value())
//                        .message("N??o autorizado")
//                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("N??o encontrado")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno no servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Recurso n??o possui representa????o que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Requisi????o recusada porque corpo est?? em um formato n??o suportado")
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Requisi????o inv??lida (erro do cliente)")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Erro interno no servidor")
                        .responseModel(new ModelRef("Problema"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.CONFLICT.value())
                        .message("Recurso n??o pode ser excluido pois est?? em uso")
                        .responseModel(new ModelRef("Problema"))
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("GreatMeals API")
                .description("Api aberta para clientes e restuarnates")
                .version("1")
                .contact(new Contact("GreatMeals", "https://www.greatmeals.com.br", "contato@greatmeals.com.br"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("http://localhost:8080/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
