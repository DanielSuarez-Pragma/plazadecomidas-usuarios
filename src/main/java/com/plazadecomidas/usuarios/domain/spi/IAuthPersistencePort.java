package com.plazadecomidas.usuarios.domain.spi;

import com.plazadecomidas.usuarios.domain.model.Auth;

import java.util.List;

public interface IAuthPersistencePort {
    Auth loginUser(String username, List<String> auths);
}
