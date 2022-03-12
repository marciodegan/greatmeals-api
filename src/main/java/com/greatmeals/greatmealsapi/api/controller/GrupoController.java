package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.GrupoInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.GrupoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.GrupoModel;
import com.greatmeals.greatmealsapi.api.model.input.GrupoInput;
import com.greatmeals.greatmealsapi.domain.model.Grupo;
import com.greatmeals.greatmealsapi.domain.repository.GrupoRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
    }

    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId) {
        return grupoModelAssembler.toModel(cadastroGrupoService.buscarOuFalhar(grupoId));
    }

    @PostMapping
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId,
                                @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);

        return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @DeleteMapping("/{grupoId}")
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }


}
