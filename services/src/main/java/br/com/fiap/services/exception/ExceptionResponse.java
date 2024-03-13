package br.com.fiap.services.exception;

import java.util.List;

public sealed interface ExceptionResponse {
    record ValidationFieldErrors(List<ValidationFieldError> errors) implements ExceptionResponse {}
    record ExceptionMessage(String error) implements ExceptionResponse {}
}
