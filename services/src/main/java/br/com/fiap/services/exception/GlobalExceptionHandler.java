package br.com.fiap.services.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private List<FieldMessage> getFieldMessages(MethodArgumentNotValidException exception) {
        List<FieldMessage> invalidParams = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach((error) -> {
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            invalidParams.add(new FieldMessage(error.getField(), errorMessage));
        });

        return invalidParams;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ProblemDetail> validationError(MethodArgumentNotValidException exception) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<FieldMessage> invalidParams = getFieldMessages(exception);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getLocalizedMessage());
        problemDetail.setType(URI.create("http://localhost:8083/erro-de-validacao"));
        problemDetail.setTitle("Erro de validação");
        problemDetail.setDetail("Um ou mais campos estão com dados incorretos ou o dado já existe");
        problemDetail.setProperty("invalidParams", invalidParams);

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ProblemDetail> controllerNotfound(NotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        problemDetail.setType(URI.create("http://localhost:8083/not-found"));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ProblemDetail> exception(Exception exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        problemDetail.setType(URI.create("http://localhost:8083/bad-request"));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problemDetail);
    }
}
