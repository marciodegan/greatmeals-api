package com.greatmeals.greatmealsapi.infrastructure.service.email;

import com.greatmeals.greatmealsapi.core.email.EmailProperties;
import com.greatmeals.greatmealsapi.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freeMarkerConfig;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            String corpo = processarTemplate(mensagem);

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setSubject(mensagem.getAssunto());
            helper.setText(corpo, true);
            helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
            helper.setFrom(emailProperties.getRemetente());

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Nao foi possivel enviar email", e);
        }
    }

    private String processarTemplate(Mensagem mensagem) {

        try {
            Template template = freeMarkerConfig.getTemplate(mensagem.getCorpo());
            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Nao foi possivel processar o email", e);
        }
    }
}
