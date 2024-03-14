package br.com.fiap.booking.mailer;

import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class MailService {

    @Value("${spring.mail.fromName}")
    private String fromName;

    @Value("${spring.mail.fromEmail}")
    private String fromEmail;

    private final Configuration config;
    private final JavaMailSender mailSender;

    public MailService(Configuration config, JavaMailSender mailSender) {
        this.config = config;
        this.mailSender = mailSender;
    }

    public void sendReservationConfirmation(String clientName, String clientEmail, LocalDate checkIn, LocalDate checkOut) {
        Mail reserveConfirmation = new Mail(fromName, fromEmail, clientEmail, "Reserva realizada com sucesso!", "reserveConfirmation.ftl")
                .with("clientName", clientName)
                .with("checkIn", checkIn)
                .with("checkOut", checkOut);
        sendMail(reserveConfirmation);
    }

    public void sendMail(Mail mail) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = config.getTemplate(mail.getTemplateName());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());

            helper.setFrom(mail.getFromEmail(), mail.getFromName());

            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException("Mail Sending failure", e);
        }
    }
}
