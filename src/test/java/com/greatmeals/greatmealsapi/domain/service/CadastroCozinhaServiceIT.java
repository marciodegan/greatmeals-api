package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.CozinhaNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaServiceIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
//    public void whenCadastroCozinhaComDadosCorretos_ThenDeveAtribuirId() {
    public void shouldAtribuirId_WhenCadastrarCozinhaComDadosCorretos() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Brasileira");

        // ação
        Cozinha novaCozinhaSalva = cadastroCozinhaService.salvar(novaCozinha);

        // validação
        assertNotNull(novaCozinhaSalva);
        assertNotNull(novaCozinhaSalva.getId());
    }

    @Test
    public void shouldFail_WhenCadastrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        ConstraintViolationException erroEsperado =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
                    cadastroCozinhaService.salvar(novaCozinha);
                });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void shouldFail_WhenRemoverUmCozinhaInexistente() {
        CozinhaNaoEncontradaException erroEsperado =
                Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
                    cadastroCozinhaService.excluir(1000L);
                });
    }

    @Test
    public void shouldFail_WhenRemoverUmCozinhaEmUso() {
        EntidadeEmUsoException erroEsperado =

                Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
                    cadastroCozinhaService.excluir(1L);
                });
    }

// Spring versao antes da 2.4.0
//    @Test(expected = ConstraintViolationException.class)
//    public void deveFalharAoCadastrarCozinha_QuandoSemNome() {
//        Cozinha novaCozinha = new Cozinha();
//        novaCozinha.setNome("");
//
//        Cozinha novaCozinhaSalva = cadastroCozinhaService.salvar(novaCozinha);
//    }



}