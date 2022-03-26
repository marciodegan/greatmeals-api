package com.greatmeals.greatmealsapi.infrastructure.service.email;

import com.greatmeals.greatmealsapi.core.email.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService {

    @Autowired
    private EmailProperties emailProperties;

    @Override
    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.criarMimeMessage(mensagem);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTE-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());

        System.out.println("passou pelo Sandbox" + mimeMessage);

        return mimeMessage;
    }
}
