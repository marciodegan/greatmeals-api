package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.CozinhaInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.CozinhaModelAssembler;
import com.greatmeals.greatmealsapi.api.model.CozinhaModel;
import com.greatmeals.greatmealsapi.api.model.input.CozinhaInput;
import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.repository.CozinhaRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<CozinhaModel> listar() {
        return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable("cozinhaId") Long id) {
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {

        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);

        return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public CozinhaModel atualizar(@PathVariable Long id,
                                  @RequestBody @Valid CozinhaInput cozinhaInput) {

        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(id);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinha);

        return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCozinhaService.excluir(id);
    }
}
