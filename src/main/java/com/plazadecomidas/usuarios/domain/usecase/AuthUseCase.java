package com.plazadecomidas.usuarios.domain.usecase;

import com.plazadecomidas.usuarios.domain.api.IAuthServicePort;
import com.plazadecomidas.usuarios.domain.exception.AuthException;
import com.plazadecomidas.usuarios.domain.model.Auth;
import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.domain.model.UserAuth;
import com.plazadecomidas.usuarios.domain.spi.IAuthPersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.plazadecomidas.usuarios.domain.constants.AuthAuthotitiesConts.*;
import static com.plazadecomidas.usuarios.domain.constants.AuthErrorMessages.*;
import static com.plazadecomidas.usuarios.domain.constants.Roles.*;

@AllArgsConstructor
public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public Auth loginUser(UserAuth userAuth) {
        User user = userPersistencePort.getUserByEmail(userAuth.getEmail());
        if(user == null) {
            throw new AuthException(USERNAME_NOT_EXIST);
        }
        if(!userPasswordEncoderPort.matches(userAuth.getPassword(), user.getPassword())) {
            throw new AuthException(INVALID_PASSWORD);
        }

        List<String> authorities = new ArrayList<>();

        authorities.add(ROLE_AUTH + user.getRole().getName());

        // Obtener el ID del rol del usuario
        Long roleId = user.getRole().getId();

        // Asignar autoridad según el ID del rol
        switch (roleId.intValue()) {
            case 0:
                authorities.add(ADMIN);
                break;
            case 1:
                authorities.add(OWNER);
                break;
            case 2:
                authorities.add(EMPLOYEE);
                break;
            case 3:
                authorities.add(CLIENT);
                break;
            default:
                throw new AuthException(ROLE_NOT_EXIST);
        }

        Auth auth = authPersistencePort.loginUser(userAuth.getEmail(), authorities);
        auth.setMessage(LOGGED_IN);
        auth.setStatus(LOGGED_IN_SUCCESS);

        return auth;
    }

}
