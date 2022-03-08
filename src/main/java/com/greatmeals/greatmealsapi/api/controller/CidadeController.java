package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.domain.exception.EstadoNaoEncontradoException;
import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.model.Cidade;
import com.greatmeals.greatmealsapi.domain.repository.CidadeRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    private CadastroCidadeService cidadeService;

    public CidadeController(CidadeRepository cidadeRepository, CadastroCidadeService cidadeService) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return cidadeService.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
        try {
            return cidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId,
                            @RequestBody @Valid Cidade cidade) {
        try {
            Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cidadeService.salvar(cidadeAtual);

        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cidadeService.excluir(cidadeId);
    }


}
