package br.com.fiap.users.exception;

public record ValidationFieldError(String field, String message) {
}
