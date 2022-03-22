package com.greatmeals.greatmealsapi.domain.service;

import java.io.InputStream;

public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;

        public NovaFoto(String nomeArquivo, InputStream inputStream) {
            this.nomeArquivo = nomeArquivo;
            this.inputStream = inputStream;
        }

        public String getNomeArquivo() {
            return nomeArquivo;
        }

        public void setNomeArquivo(String nomeArquivo) {
            this.nomeArquivo = nomeArquivo;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }
    }
}

/*
 *  Poderia receber o MultipartFile como parâmetro, mas tem a ver com protocolo http/web
    e não faz sentido colocar dentro do dominio da aplicação
 *  Então um melhor approach é criar uma classe interna NovaFoto e dentro dela vamos ter o que precisamos
 *  para cadastrar uma nova foto.
 *  InputStream é o fluxo de entrada do arquivo, o fluxo de leitura.
 *  Entao para armazenar um arquivo no servico de storage, basta chamar o método armazenar passando uma instância de
 *  nova foto.
 *  */