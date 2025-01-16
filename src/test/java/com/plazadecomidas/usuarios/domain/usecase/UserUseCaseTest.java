package com.plazadecomidas.usuarios.domain.usecase;

import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IUserPasswordEncoderPort userPasswordEncoderPort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(
                1L,
                "Carlos",
                "Rodriguez",
                "123456789",
                "+573456789012",
                LocalDate.of(1990, 1, 1),
                "carlos.rodriguez@example.com",
                "password123",
                new Role(1L, "OWNER", "Owner test")
        );
    }

}