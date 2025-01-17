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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
                new Role(1L, "PROPIETARIO", "Owner test")
        );
    }

    @Test
    void saveUserOwner_Success() {
        Role ownerRole = new Role(1L, "PROPIETARIO", "PROPIETARIO role");
        when(rolePersistencePort.findRoleByName("PROPIETARIO")).thenReturn(ownerRole);
        when(userPasswordEncoderPort.encodePassword(user.getPassword())).thenReturn("password123");

        userUseCase.saveUserOwner(user);

        verify(rolePersistencePort).findRoleByName("PROPIETARIO");
        verify(userPasswordEncoderPort).encodePassword(user.getPassword());
        verify(userPersistencePort).saveUserOwner(user);
    }


    @Test
    void saveUserEmployee_Success() {
        user.setRole(new Role(2L, "EMPLEADO", "EMPLEADO role"));
        when(rolePersistencePort.findRoleByName("EMPLEADO")).thenReturn(user.getRole());
        when(userPasswordEncoderPort.encodePassword(user.getPassword())).thenReturn("password123");

        userUseCase.saveUserEmployee(user);

        verify(rolePersistencePort).findRoleByName("EMPLEADO");
        verify(userPasswordEncoderPort).encodePassword(user.getPassword());
        verify(userPersistencePort).saveUserEmployee(user);
    }

    @Test
    void saveUserClient_Success() {
        user.setRole(new Role(3L, "CLIENTE", "CLIENTE role"));
        when(rolePersistencePort.findRoleByName("CLIENTE")).thenReturn(user.getRole());
        when(userPasswordEncoderPort.encodePassword(user.getPassword())).thenReturn("password123");

        userUseCase.saveUserClient(user);

        verify(rolePersistencePort).findRoleByName("CLIENTE");
        verify(userPasswordEncoderPort).encodePassword(user.getPassword());
        verify(userPersistencePort).saveUserClient(user);
    }

    @Test
    void getUser_Success() {
        when(userPersistencePort.getUser(1L)).thenReturn(user);

        User result = userUseCase.getUser(1L);

        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        verify(userPersistencePort).getUser(1L);
    }

    @Test
    void getUserByEmail_Success() {
        when(userPersistencePort.getUserByEmail(user.getEmail())).thenReturn(user);

        User result = userUseCase.getUserByEmail(user.getEmail());

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        verify(userPersistencePort).getUserByEmail(user.getEmail());
    }
}