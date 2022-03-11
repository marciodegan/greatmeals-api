package com.greatmeals.greatmealsapi.api.controller;

import com.greatmeals.greatmealsapi.api.assembler.FormaPagamentoInputDisassembler;
import com.greatmeals.greatmealsapi.api.assembler.FormaPagamentoModelAssembler;
import com.greatmeals.greatmealsapi.api.model.FormaPagamentoModel;
import com.greatmeals.greatmealsapi.api.model.input.FormaPagamentoInput;
import com.greatmeals.greatmealsapi.domain.model.FormaPagamento;
import com.greatmeals.greatmealsapi.domain.repository.FormaPagamentoRepository;
import com.greatmeals.greatmealsapi.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamentos")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        return formaPagamentoModelAssembler.toModel(formaPagamentoService.buscarOuFalhar(formaPagamentoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {

        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{formasPagamentoId")
    public FormaPagamentoModel atualizar(@PathVariable Long formasPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formasPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamento);

        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
