package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.RestauranteNaoEncontradoException;
import com.greatmeals.greatmealsapi.domain.model.*;
import com.greatmeals.greatmealsapi.domain.repository.FormaPagamentoRepository;
import com.greatmeals.greatmealsapi.domain.repository.ProdutoRepository;
import com.greatmeals.greatmealsapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_ESTÁ_EM_USO
            = "Restaurante de código %d não pode ser removido, pois já está em uso";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroCidadeService cidadeService;

    @Autowired
    private CadastroFormaPagamentoService formaPagamentoService;

    @Autowired
    private CadastroProdutoService produtoService;

    @Autowired
    private CadastroUsuarioService usuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaService.buscarOuFalhar(restaurante.getCozinha().getId());
        Cidade cidade = cidadeService.buscarOuFalhar(restaurante.getEndereco().getCidade().getId());
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void excluir(Long restauranteId) {
        try {
            restauranteRepository.deleteById(restauranteId);
            restauranteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(restauranteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_ESTÁ_EM_USO, restauranteId));
        }
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.inativar();
    }


    @Transactional  // devido o @Transactional, não é necessário dar um save aqui.
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

//        restaurante.getFormasPagamento().remove(formaPagamento);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.abrirLoja();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        restaurante.fecharLoja();
    }

    @Transactional
    public void associarUsuario(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        restaurante.adicionarUsuario(usuario);
    }

    @Transactional
    public void desassociarUsuario(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        restaurante.removerUsuario(usuario);
    }

    @Transactional
    public void ativar(List<Long> restaurantesIds) {
        restaurantesIds.forEach(this::ativar);
    }

    @Transactional
    public void desativar(List<Long> restaurantesIds) {
        restaurantesIds.forEach(this::inativar);
    }


    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

}
