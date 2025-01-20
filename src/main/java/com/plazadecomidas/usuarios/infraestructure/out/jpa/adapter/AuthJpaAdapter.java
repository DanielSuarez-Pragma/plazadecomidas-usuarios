package com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter;

import com.plazadecomidas.usuarios.domain.model.Auth;
import com.plazadecomidas.usuarios.domain.spi.IAuthPersistencePort;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import com.plazadecomidas.usuarios.infraestructure.security.util.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthJpaAdapter implements IAuthPersistencePort, UserDetailsService {

    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final List<SimpleGrantedAuthority> authorities;

    public AuthJpaAdapter(IUserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.authorities = new ArrayList<>();
    }

    @Override
    public Auth loginUser(String username, List<String> auths) {
        setAuthorities(auths);
        Authentication authentication = authenticate(username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new Auth(username, null, createToken(authentication), null);
    }

    private void setAuthorities(List<String> auths) {
        auths.forEach(auth -> authorities.add(new SimpleGrantedAuthority(auth)));
    }

    private String createToken(Authentication authentication) {
        return jwtUtils.createToken(authentication);
    }

    private Authentication authenticate(String email) {
        UserDetails userDetails = loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}