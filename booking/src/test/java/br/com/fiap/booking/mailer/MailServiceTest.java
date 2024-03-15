package br.com.fiap.booking.mailer;

import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MailServiceTest {

    private Configuration config;
    private JavaMailSender mailSender;

    private MailService mailService;

    @BeforeEach
    void setUp() {
        config = Mockito.mock(Configuration.class);
        mailSender = Mockito.mock(JavaMailSender.class);

        mailService = new MailService(config, mailSender);

        ReflectionTestUtils.setField(mailService, "fromName", "John Doe");
        ReflectionTestUtils.setField(mailService, "fromEmail", "jd@mail.com");
    }

    @Test
    void shouldSendReservationConfirmation() throws Exception {
        String clientName = "John Doe";
        String clientEmail = "john.doe@example.com";
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now().plusDays(5);

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        Template template = Mockito.mock(Template.class);
        when(config.getTemplate(anyString())).thenReturn(template);

        mailService.sendReservationConfirmation(clientName, clientEmail, checkIn, checkOut);

        verify(mailSender).send(any(MimeMessage.class));
    }

    @Test
    void shouldThrowExceptionWhenTemplateNotFound() throws Exception {
        String clientName = "John Doe";
        String clientEmail = "john.doe@example.com";
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now().plusDays(5);

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        when(config.getTemplate(anyString())).thenThrow(new TemplateNotFoundException("template", null, ""));

        assertThrows(RuntimeException.class, () -> mailService.sendReservationConfirmation(clientName, clientEmail, checkIn, checkOut));
    }

    @Test
    void shouldThrowExceptionWhenMailSendingFails() throws Exception {
        String clientName = "John Doe";
        String clientEmail = "john.doe@example.com";
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = LocalDate.now().plusDays(5);

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        Template template = Mockito.mock(Template.class);
        when(config.getTemplate(anyString())).thenReturn(template);

        doThrow(new MailSendException("error")).when(mailSender).send(any(MimeMessage.class));

        assertThrows(RuntimeException.class, () -> mailService.sendReservationConfirmation(clientName, clientEmail, checkIn, checkOut));
    }
}