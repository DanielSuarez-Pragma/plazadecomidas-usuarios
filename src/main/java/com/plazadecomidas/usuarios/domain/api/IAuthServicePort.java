package com.plazadecomidas.usuarios.domain.api;

import com.plazadecomidas.usuarios.domain.model.AuthResponse;
import com.plazadecomidas.usuarios.domain.model.UserAuth;

public interface IAuthServicePort {

    AuthResponse loginUser(UserAuth userAuth);
}
