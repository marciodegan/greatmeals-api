package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.PermissaoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.PermissaoModel;
import com.greatmeals.greatmealsapi.domain.model.Grupo;
import com.greatmeals.greatmealsapi.domain.service.CadastroGrupoService;
import com.greatmeals.greatmealsapi.domain.service.CadastroPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupoService grupoService;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private CadastroPermissaoService permissaoService;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @PutMapping("/{permissoesId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissoesId) {
        grupoService.associarPermissao(grupoId, permissoesId);
    }

    @DeleteMapping("/{permissoesId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissoesId) {
        grupoService.desassociarPermissao(grupoId, permissoesId);
    }
}