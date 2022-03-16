package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.GrupoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.GrupoModel;
import com.greatmeals.greatmealsapi.domain.model.Usuario;
import com.greatmeals.greatmealsapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public Collection<GrupoModel> listar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
