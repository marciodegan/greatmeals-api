package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.FotoProdutoNaoEncontradaException;
import com.greatmeals.greatmealsapi.domain.model.FotoProduto;
import com.greatmeals.greatmealsapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {

        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }
        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = new FotoStorageService.NovaFoto(
                foto.getNomeArquivo(),
                dadosArquivo);

        fotoStorageService.armazenar(novaFoto);
        return foto;
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradaException(produtoId, restauranteId));
    }
}
