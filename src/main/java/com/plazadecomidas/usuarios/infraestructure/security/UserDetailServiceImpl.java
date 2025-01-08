package com.plazadecomidas.usuarios.infraestructure.security;

import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private IUserRepository userRepository;

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
}
