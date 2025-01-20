package com.plazadecomidas.usuarios.infraestructure.configuration;

import com.plazadecomidas.usuarios.domain.api.IAuthServicePort;
import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.spi.IAuthPersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import com.plazadecomidas.usuarios.domain.usecase.AuthUseCase;
import com.plazadecomidas.usuarios.domain.usecase.UserUseCase;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter.AuthJpaAdapter;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter.UserJpaAdapter;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.RoleEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IRoleRepository;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import com.plazadecomidas.usuarios.infraestructure.out.passwordencode.PasswordEncoderAdapter;
import com.plazadecomidas.usuarios.infraestructure.security.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final JwtUtils jwtUtils;

    @Bean
    public IUserPasswordEncoderPort userPasswordEncoderPort() {
        return new PasswordEncoderAdapter();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(),rolePersistencePort(),userPasswordEncoderPort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        return new AuthJpaAdapter(userRepository ,jwtUtils);
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(authPersistencePort(), userPasswordEncoderPort(), userPersistencePort());
    }

}
