package com.greatmeals.greatmealsapi.infrastructure.service.storage;

import com.greatmeals.greatmealsapi.domain.service.FotoStorageService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3FotoStorageService implements FotoStorageService {
    @Override
    public InputStream recuperar(String nomeArquivo) {
        return null;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {

    }

    @Override
    public void excluir(NovaFoto novaFoto) {

    }
}
