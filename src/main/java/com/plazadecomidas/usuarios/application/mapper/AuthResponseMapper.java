package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.auth.AuthResponseDto;
import com.plazadecomidas.usuarios.domain.model.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AuthResponseMapper {
    default  AuthResponseDto toResponse(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setUsername(auth.getUsername());
        authResponseDto.setMessage(auth.getMessage());
        authResponseDto.setTokenJWT(auth.getTokenJWT());
        authResponseDto.setStatus(auth.getStatus());
        return authResponseDto;
    }

}
