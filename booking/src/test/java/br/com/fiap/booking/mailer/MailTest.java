package br.com.fiap.booking.mailer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MailTest {

    private Mail mail;

    @BeforeEach
    void setUp() {
        mail = new Mail("John Doe", "john.doe@example.com", "jane.doe@example.com", "Test Subject", "template");
    }

    @Test
    void shouldReturnCorrectFromName() {
        assertEquals("John Doe", mail.getFromName());
    }

    @Test
    void shouldReturnCorrectFromEmail() {
        assertEquals("john.doe@example.com", mail.getFromEmail());
    }

    @Test
    void shouldReturnCorrectTo() {
        assertEquals("jane.doe@example.com", mail.getTo());
    }

    @Test
    void shouldReturnCorrectSubject() {
        assertEquals("Test Subject", mail.getSubject());
    }

    @Test
    void shouldReturnCorrectTemplateName() {
        assertEquals("template", mail.getTemplateName());
    }

    @Test
    void shouldAddModelEntry() {
        mail.with("key", "value");
        assertEquals("value", mail.getModel().get("key"));
    }

    @Test
    void shouldReturnEmptyModelIfNoEntryAdded() {
        assertTrue(mail.getModel().isEmpty());
    }
}