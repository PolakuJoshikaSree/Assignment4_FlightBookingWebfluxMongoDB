package com.flightapp.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testValidationException() {
        BindException bindException = new BindException(new Object(), "object");
        bindException.addError(new FieldError("object", "field", "must not be empty"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException.getBindingResult());

        ResponseEntity<Object> response = handler.handleValidation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("must not be empty"));
    }

    @Test
    void testResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Not found");

        ResponseEntity<Object> response = handler.notFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found", response.getBody());
    }

    @Test
    void testCustomValidationException() {
        ValidationException ex = new ValidationException("Invalid value");

        ResponseEntity<Object> response = handler.customValidation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid value", response.getBody());
    }

    @Test
    void testGenericException() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<Object> response = handler.global(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Something went wrong"));
    }
}
