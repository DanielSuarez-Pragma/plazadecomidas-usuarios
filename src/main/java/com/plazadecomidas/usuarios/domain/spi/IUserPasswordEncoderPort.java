package com.plazadecomidas.usuarios.domain.spi;

public interface IUserPasswordEncoderPort {
    String encodePassword(String password);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
