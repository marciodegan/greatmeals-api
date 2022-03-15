package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.UsuarioInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.UsuarioModelAssembler;
import com.greatmeals.greatmealsapi.api.model.UsuarioModel;
import com.greatmeals.greatmealsapi.api.model.input.SenhaInput;
import com.greatmeals.greatmealsapi.api.model.input.UsuarioInput;
import com.greatmeals.greatmealsapi.domain.exception.NegocioException;
import com.greatmeals.greatmealsapi.domain.model.Usuario;
import com.greatmeals.greatmealsapi.domain.repository.UsuarioRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @GetMapping
    public List<UsuarioModel> listar() {
        return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
        System.out.println(usuarioInput);
        Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
        return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        if (usuario.senhaDoesNotMatch(usuarioInput.getSenha())) {
            throw new NegocioException("Senha não está correta");
        }

        usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuario);

        return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        cadastroUsuarioService.excluir(usuarioId);
    }


    @PutMapping("/{usuarioId}/alteracaosenha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId,
                             @RequestBody @Valid SenhaInput senha) {
        cadastroUsuarioService.alteraSenha(usuarioId, senha.getSenhaAtual(), senha.getSenhaNova());
    }

}
