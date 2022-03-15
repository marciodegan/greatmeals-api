package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.EntidadeEmUsoException;
import com.greatmeals.greatmealsapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.exception.ProdutoNaoEncontradoException;
import com.greatmeals.greatmealsapi.domain.model.FormaPagamento;
import com.greatmeals.greatmealsapi.domain.model.Produto;
import com.greatmeals.greatmealsapi.domain.repository.FormaPagamentoRepository;
import com.greatmeals.greatmealsapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {

    public static final String MSG_FORMA_PAGAMENTO_EM_USO
            = "Produto com o código %d não pode ser removida, pois já está em uso";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

//    @Transactional
//    public void excluir(Long produtoId) {
//        try {
//            produtoRepository.deleteById(produtoId);
//            produtoRepository.flush();
//        } catch (EmptyResultDataAccessException e) {
//            throw new ProdutoNaoEncontradoException(produtoId);
//        } catch (DataIntegrityViolationException e) {
//            throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, produtoId));
//        }
//    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }


}
