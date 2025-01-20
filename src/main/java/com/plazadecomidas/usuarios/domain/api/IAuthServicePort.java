package com.plazadecomidas.usuarios.domain.api;

import com.plazadecomidas.usuarios.domain.model.Auth;
import com.plazadecomidas.usuarios.domain.model.UserAuth;

public interface IAuthServicePort {

    Auth loginUser(UserAuth userAuth);
}
