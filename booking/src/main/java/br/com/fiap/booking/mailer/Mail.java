package br.com.fiap.booking.mailer;

import java.util.*;

public class Mail {

    private final String fromName;
    private final String fromEmail;
    private final String to;
    private final String subject;
    private final Map<String, Object> model = new HashMap<>();
    private final String templateName;

    public Mail(String fromName, String fromEmail, String to, String subject, String templateName) {
        this.fromName = fromName;
        this.fromEmail = fromEmail;
        this.to = to;
        this.subject = subject;
        this.templateName = templateName;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public String getTemplateName() {
        return templateName;
    }

    public Mail with(String key, Object object) {
        model.put(key, object);
        return this;
    }
}
