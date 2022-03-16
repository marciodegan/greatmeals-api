package com.greatmeals.greatmealsapi.domain.service;

import com.greatmeals.greatmealsapi.domain.exception.PermissaoNaoEncontradoException;
import com.greatmeals.greatmealsapi.domain.model.Permissao;
import com.greatmeals.greatmealsapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroPermissaoService {

    public static final String MSG_PERSMISSAO_EM_USO
            = "Permissão com o código %d não pode ser removida, pois já está em uso";


    @Autowired
    private PermissaoRepository permissaoRepository;

    @Transactional
    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
    }
}