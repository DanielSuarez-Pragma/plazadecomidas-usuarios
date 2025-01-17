package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.auth.AuthLoginRequestDto;
import com.plazadecomidas.usuarios.application.dto.auth.AuthResponseDto;
import com.plazadecomidas.usuarios.application.mapper.AuthRequestMapper;
import com.plazadecomidas.usuarios.application.mapper.AuthResponseMapper;
import com.plazadecomidas.usuarios.domain.api.IAuthServicePort;
import com.plazadecomidas.usuarios.domain.model.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthListHandler implements IAuthHandler{

    private final AuthRequestMapper authRequestMapper;
    private final AuthResponseMapper authResponseMapper;
    private final IAuthServicePort authServicePort;

    @Override
    public AuthResponseDto loginUser(AuthLoginRequestDto userRequest) {
        AuthResponse authResponse = authServicePort.loginUser(authRequestMapper.toUserAuth(userRequest));
        return authResponseMapper.toResponse(authResponse);
    }

    @Override
    public AuthResponseDto registerClient(String email, String password) {
        AuthLoginRequestDto authLoginRequestDto = new AuthLoginRequestDto(email, password);
        AuthResponse authResponse = authServicePort.loginUser(authRequestMapper.toUserAuth(authLoginRequestDto));
        return authResponseMapper.toResponse(authResponse);
    }
}
