package com.plazadecomidas.usuarios.infraestructure.configuration;

import com.plazadecomidas.usuarios.domain.api.IRoleServicePort;
import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import com.plazadecomidas.usuarios.domain.usecase.RoleUseCase;
import com.plazadecomidas.usuarios.domain.usecase.UserUseCase;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.RoleEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IRoleRepository;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoder());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }
}
