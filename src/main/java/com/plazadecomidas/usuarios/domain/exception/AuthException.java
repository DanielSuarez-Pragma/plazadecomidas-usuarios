package com.plazadecomidas.usuarios.domain.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
