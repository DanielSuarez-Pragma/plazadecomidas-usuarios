package com.plazadecomidas.usuarios.infraestructure.security;

import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import com.plazadecomidas.usuarios.infraestructure.security.dto.AuthLoginRequest;
import com.plazadecomidas.usuarios.infraestructure.security.dto.AuthResponse;
import com.plazadecomidas.usuarios.infraestructure.security.util.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final IUserRepository userRepository;

    public UserDetailServiceImpl(PasswordEncoder passwordEncoder, IUserRepository userRepository, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByEmail(username).orElseThrow(()->new UsernameNotFoundException("El usuario "+username+" no existe"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getName()));

        // Obtener el ID del rol del usuario
        Long roleId = userEntity.getRole().getId();

        // Asignar autoridad según el ID del rol
        switch (roleId.intValue()) {
            case 0:
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                break;
            case 1:
                authorities.add(new SimpleGrantedAuthority("OWNER"));
                break;
            case 3:
                authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
                break;
            case 4:
                authorities.add(new SimpleGrantedAuthority("CLIENT"));
                break;
            default:
                throw new IllegalArgumentException("Rol no reconocido para el ID: " + roleId);
        }


        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User loged successfuly", accessToken, true);

    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
