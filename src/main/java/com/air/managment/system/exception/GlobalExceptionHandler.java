package com.air.managment.system.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotExistException.class)
    public ResponseEntity<ExceptionMessage> handleAirCompanyNotFoundException(@NonNull final HttpServletRequest request, @NonNull final EntityNotExistException ex) {
        log.error("EntityNotExistException occurred:", ex);
        final var message = ExceptionMessage.builder().status(HttpStatus.NOT_FOUND).date(new Date()).description(ex.getMessage()).url(request.getRequestURL().toString()).build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ExceptionMessage> handleAirCompanyNotFoundException(@NonNull final HttpServletRequest request, @NonNull final EntityAlreadyExistException ex) {
        log.error("EntityAlreadyExistException occurred:", ex);
        final var message = ExceptionMessage.builder().status(HttpStatus.BAD_REQUEST).date(new Date()).description(ex.getMessage()).url(request.getRequestURL().toString()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExceptionMessage> handleResourceNotFoundException(@NonNull final HttpServletRequest request, @NonNull final Exception exception) {
        log.error("Exception was thrown because passed data was not valid:", exception);
        final var message = ExceptionMessage.builder().status(HttpStatus.BAD_REQUEST).date(new Date()).description(exception.getMessage()).url(request.getRequestURL().toString()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConversionFailedException(ConversionFailedException ex) {
        String errorMessage = "Failed to convert request parameter: " + ex.getValue();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionMessage> handleCIllegalArgumentException(IllegalArgumentException ex) {
        final var message =
                ExceptionMessage.builder().status(HttpStatus.BAD_REQUEST)
                .date(new Date())
                .description("Illegal Argument Exception: " + ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(final Exception ex) {
        log.error("An unexpected error occurred:", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }

    /**
     * To catch MethodArgumentNotValidException we need to override method
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("MethodArgumentNotValidException occurred:", ex);
        List<ExceptionMessage.SimplifiedError> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> new ExceptionMessage.SimplifiedError(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        final ExceptionMessage errorDetails = ExceptionMessage.builder().status(HttpStatus.BAD_REQUEST).description("Validation failed for arguments").date(new Date()).url(getRequestURL(request)).errors(errors).build();
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

    private String getRequestURL(WebRequest request) {
        HttpServletRequest servletRequest = ((ServletWebRequest) request).getRequest();
        return servletRequest.getRequestURL().toString();
    }

}
