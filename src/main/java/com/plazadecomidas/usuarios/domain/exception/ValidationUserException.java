package com.plazadecomidas.usuarios.domain.exception;

public class ValidationUserException extends RuntimeException {
    public ValidationUserException(String message) {
        super(message);
    }
}
