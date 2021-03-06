package com.greatmeals.greatmealsapi.domain.service;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {

    FotoRecuperada recuperar(String nomeArquivo);

    void armazenar(NovaFoto novaFoto);

    void excluir(String nomeArquivo);

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID().toString() + "_" + nomeOriginal;
    }

    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;


        public NovaFoto(String nomeArquivo, InputStream inputStream) {
            this.nomeArquivo = nomeArquivo;
            this.inputStream = inputStream;
        }

        public NovaFoto(String nomeArquivo) {
            this.nomeArquivo = nomeArquivo;
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

    class FotoRecuperada {
        private InputStream inputStream;
        private String url;

        public FotoRecuperada(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public FotoRecuperada(String url) {
            this.url = url;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean temUrl() {
            return url != null;
        }

        public boolean temInputStream() {
            return inputStream != null;
        }
    }
}

/*
 *  Poderia receber o MultipartFile como par??metro, mas tem a ver com protocolo http/web
    e n??o faz sentido colocar dentro do dominio da aplica????o
 *  Ent??o um melhor approach ?? criar uma classe interna NovaFoto e dentro dela vamos ter o que precisamos
 *  para cadastrar uma nova foto.
 *  InputStream ?? o fluxo de entrada do arquivo, o fluxo de leitura.
 *  Entao para armazenar um arquivo no servico de storage, basta chamar o m??todo armazenar passando uma inst??ncia de
 *  nova foto.
 *  */