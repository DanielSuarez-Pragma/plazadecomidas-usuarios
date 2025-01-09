package com.plazadecomidas.usuarios.infraestructure.security.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message", "jwt","status"})
public record AuthResponse(String username, String message, String jwt, Boolean status) {
}
