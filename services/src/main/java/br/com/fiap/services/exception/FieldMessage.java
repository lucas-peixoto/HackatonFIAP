package br.com.fiap.services.exception;

public class FieldMessage {

    private String field;
    private String message;

    public FieldMessage() {
    }

    public FieldMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}