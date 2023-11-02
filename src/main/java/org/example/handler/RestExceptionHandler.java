package org.example.handler;

import jakarta.validation.constraints.NotNull;
import org.example.exception.BadRequestException;
import org.example.exception.BadRequestExceptionDetails;
import org.example.exception.ExceptionDetails;
import org.example.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception. Check the documentation for more information.")
                        .details(badRequestException.getMessage())
                        .developerMessage("This exception was thrown due to some misunderstanding about how the API works.")
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NotNull MethodArgumentNotValidException methodArgumentNotValidException,
            @NotNull HttpHeaders httpHeaders,
            @NotNull HttpStatusCode httpStatusCode,
            @NotNull WebRequest webRequest
    ) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String messages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception thrown due to invalid fields. Check the documentation for more information.")
                        .details(methodArgumentNotValidException.getMessage())
                        .developerMessage("This exception was thrown due to some misunderstanding about how the API works.")
                        .fields(fields)
                        .fieldsMessage(messages)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            @Nullable Object body,
            @NotNull HttpHeaders httpHeaders,
            HttpStatusCode httpStatusCode,
            @NotNull WebRequest webRequest
    ) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatusCode.value())
                .title(String.format("A '%s' has been thrown", exception.getClass().getName()))
                .details(String.format("A '%s' has been thrown with the following cause: %s", exception.getClass().getName(), exception.getCause().getClass().getName()))
                .developerMessage("This is an internal exception. Check the documentation for more information or contact the system administrator.")
                .build();
        return new ResponseEntity<>(exceptionDetails, httpHeaders, httpStatusCode);
    }
}
