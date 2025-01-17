package com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter;

import com.plazadecomidas.usuarios.domain.spi.IAuthPersistencePort;
import com.plazadecomidas.usuarios.infraestructure.security.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;

@AllArgsConstructor
public class AuthJpaAdapter implements IAuthPersistencePort {

    private JwtUtils jwtUtils;

    @Override
    public String createToken(Authentication authentication) {
        return jwtUtils.createToken(authentication);
    }
}
