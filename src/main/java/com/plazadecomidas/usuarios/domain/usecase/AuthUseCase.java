package com.plazadecomidas.usuarios.domain.usecase;

import com.plazadecomidas.usuarios.domain.api.IAuthServicePort;
import com.plazadecomidas.usuarios.domain.exception.AuthException;
import com.plazadecomidas.usuarios.domain.model.AuthResponse;
import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.domain.model.UserAuth;
import com.plazadecomidas.usuarios.domain.spi.IAuthPersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.plazadecomidas.usuarios.domain.constants.AuthAuthotitiesConts.*;
import static com.plazadecomidas.usuarios.domain.constants.AuthErrorMessages.*;
import static com.plazadecomidas.usuarios.domain.constants.Roles.*;

@Service
@AllArgsConstructor
public class AuthUseCase implements IAuthServicePort, UserDetailsService {

    private final IAuthPersistencePort authPersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public AuthResponse loginUser(UserAuth userAuth) {

        Authentication authentication = authenticate(userAuth.getEmail(), userAuth.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesToken = authPersistencePort.createToken(authentication);

        return new AuthResponse(userAuth.getEmail(), LOGGED_IN, accesToken, LOGGED_IN_SUCCESS);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if(!userPasswordEncoderPort.matches(password, userDetails.getPassword())){
            throw new AuthException(INVALID_PASSWORD);
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());

    }

    public UserDetails loadUserByUsername(String email) {
        User user = userPersistencePort.getUserByEmail(email);
        if(user == null) {
            throw new AuthException(USERNAME_NOT_EXIST);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(ROLE_AUTH + user.getRole().getName()));

        // Obtener el ID del rol del usuario
        Long roleId = user.getRole().getId();

        // Asignar autoridad según el ID del rol
        switch (roleId.intValue()) {
            case 0:
                authorities.add(new SimpleGrantedAuthority(ADMIN));
                break;
            case 1:
                authorities.add(new SimpleGrantedAuthority(OWNER));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority(EMPLOYEE));
                break;
            case 3:
                authorities.add(new SimpleGrantedAuthority(CLIENT));
                break;
            default:
                throw new AuthException(ROLE_NOT_EXIST);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
