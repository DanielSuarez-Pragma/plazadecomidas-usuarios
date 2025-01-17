package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.auth.AuthLoginRequestDto;
import com.plazadecomidas.usuarios.application.dto.auth.AuthResponseDto;
import jakarta.validation.Valid;


public interface IAuthHandler {
    AuthResponseDto loginUser(@Valid AuthLoginRequestDto userRequest);

    AuthResponseDto registerClient(String email, String password);
}
