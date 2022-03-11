package com.greatmeals.greatmealsapi.domain.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaServiceTest {

    @LocalServerPort
    private int port;

//    @Test
//    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
//        RestAssured.given()
//                .basePath("/cozinhas")
//                .port(8080)
//                .accept(ContentType.JSON)
//                .when()
//                .get()
//                .then()
//                .statusCode(200);
//    }

}
