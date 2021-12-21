package br.com.nutriCenter.services;

import br.com.nutriCenter.config.ConfigEnvioDeEmail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EnverEmailService {

    private static final String SYMBOLS = "!@#$%¨&*()=+{}[]^/?;:.,-_";
    private static final String CAP_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SMALL_LETTER = "abcdefghijklmnopqrstuvxyz";
    private static final String REMENTENTE = "qualylife2021@gmail.com";
    private static final String RECOVERY_PACIENTE = "Nova Senha: ";
    private static final String SUBJECT  = "Alteração de Senha QUALYLIFE";

    private ConfigEnvioDeEmail email;
    private SimpleMailMessage message;

	protected void sendSimpleMessage(String destinatario, String corpoDaMensagem) {
        email = ConfigEnvioDeEmail.getInstance();
        message = new SimpleMailMessage();
        message.setFrom(REMENTENTE);
        message.setTo(destinatario);
        message.setSubject(SUBJECT);
        message.setText(RECOVERY_PACIENTE+ corpoDaMensagem);
        email.getJavaMailSender().send(message);
    }

    protected String gerarSenhaAleatoria(){
        String result = "";
        String finalString = SYMBOLS + CAP_LETTER + NUMBERS + SMALL_LETTER;
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            result += finalString.charAt(random.nextInt(finalString.length()));
        }
        return result;
    }


}
