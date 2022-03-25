package com.greatmeals.greatmealsapi.infrastructure.service.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Service
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    private static final Logger logger = LogManager.getLogger(FakeEnvioEmailService.class);

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            String corpo = processarTemplate(mensagem);
            logger.info("Email enviado: " + mensagem.getDestinatarios() + corpo);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar o email");
        }
    }
}
