package com.greatmeals.greatmealsapi.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties("greatmeals.email")
@Component
public class EmailProperties {

    private Sandbox sandbox = new Sandbox();
    private Implementacao impl = Implementacao.FAKE;

    @NotNull
    private String remetente;

    public enum Implementacao {
        SMTP, FAKE, SANDBOX
    }

    public Sandbox getSandbox() {
        return sandbox;
    }

    public Implementacao getImpl() {
        return impl;
    }

    public void setImpl(Implementacao impl) {
        this.impl = impl;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public class Sandbox {

        private String destinatario;

        public String getDestinatario() {
            return destinatario;
        }

        public void setDestinatario(String destinatario) {
            this.destinatario = destinatario;
        }
    }
}
