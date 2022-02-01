package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.domain.exception.EntidadeNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.model.Cidade;
import com.greatmeals.greatmealsapi.domain.repository.CidadeRepository;
import com.greatmeals.greatmealsapi.domain.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    private CidadeService cidadeService;

    public CidadeController(CidadeRepository cidadeRepository, CidadeService cidadeService) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<?> buscar(@PathVariable Long cidadeId) {

        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

        if (cidade.isPresent()) {
            return ResponseEntity.ok(cidade.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
