package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.domain.model.Cozinha;
import com.greatmeals.greatmealsapi.domain.model.Estado;
import com.greatmeals.greatmealsapi.domain.repository.EstadoRepository;
import com.greatmeals.greatmealsapi.domain.service.EstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoRepository estadoRepository;

    private EstadoService estadoService;

    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService) {
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.todos();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return estadoRepository.porId(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
        return ResponseEntity.ok(estadoService.salvar(estado));
    }
}
