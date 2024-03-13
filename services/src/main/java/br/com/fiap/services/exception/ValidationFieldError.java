package br.com.fiap.services.exception;

public record ValidationFieldError(String field, String message) {
}
