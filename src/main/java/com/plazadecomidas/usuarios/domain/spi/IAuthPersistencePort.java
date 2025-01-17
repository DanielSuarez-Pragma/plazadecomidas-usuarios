package com.plazadecomidas.usuarios.domain.spi;

import org.springframework.security.core.Authentication;

public interface IAuthPersistencePort {
    String createToken(Authentication authentication);
}
