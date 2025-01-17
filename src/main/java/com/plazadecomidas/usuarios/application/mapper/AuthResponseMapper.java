package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.auth.AuthResponseDto;
import com.plazadecomidas.usuarios.domain.model.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AuthResponseMapper {
    default  AuthResponseDto toResponse(AuthResponse authResponse) {
        if ( authResponse == null ) {
            return null;
        }

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setUsername(authResponse.getUsername());
        authResponseDto.setMessage(authResponse.getMessage());
        authResponseDto.setTokenJWT(authResponse.getTokenJWT());
        authResponseDto.setStatus(authResponse.getStatus());
        return authResponseDto;
    }

}
