package com.plazadecomidas.usuarios.infraestructure.out.passwordencode;

import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordEncoderAdapter implements IUserPasswordEncoderPort {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
