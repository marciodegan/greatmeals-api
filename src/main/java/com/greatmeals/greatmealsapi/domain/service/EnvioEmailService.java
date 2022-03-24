package com.greatmeals.greatmealsapi.domain.service;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    class Mensagem {
        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
        private Map<String, Object> variaveis;


        public Map<String, Object> getVariaveis() {
            return variaveis;
        }

        public void setVariaveis(Map<String, Object> variaveis) {
            this.variaveis = variaveis;
        }

        public Mensagem(Set<String> destinatarios, String assunto, String corpo, Map<String, Object> variaveis) {
            this.destinatarios = destinatarios;
            this.assunto = assunto;
            this.corpo = corpo;
            this.variaveis = variaveis;
        }

        public Set<String> getDestinatarios() {
            return destinatarios;
        }

        public void setDestinatarios(Set<String> destinatarios) {
            this.destinatarios = destinatarios;
        }

        public String getAssunto() {
            return assunto;
        }

        public void setAssunto(String assunto) {
            this.assunto = assunto;
        }

        public String getCorpo() {
            return corpo;
        }

        public void setCorpo(String corpo) {
            this.corpo = corpo;
        }
    }
}
