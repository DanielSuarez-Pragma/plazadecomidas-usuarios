package com.plazadecomidas.usuarios.domain.usecase;

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
                1L
        );
    }

    // Test para guardar usuario
    @Test
    void saveUser_Success() {
        when(userPasswordEncoderPort.encodePassword(user.getPassword())).thenReturn("password123");
        when(rolePersistencePort.getRoleById(user.getRoleId())).thenReturn(new com.plazadecomidas.usuarios.domain.model.Role(1L, "Propietario","Usuario con permisos para administrar restaurantes."));

        assertDoesNotThrow(() -> userUseCase.saveUserOwner(user));

        verify(userPasswordEncoderPort, times(1)).encodePassword(user.getPassword());
        verify(userPersistencePort, times(1)).saveUserOwner(user);
    }

    // Test para buscar un usuario por ID
    @Test
    void getUser_Success() {
        when(userPersistencePort.getUser(1L)).thenReturn(user);

        User result = userUseCase.getUser(1L);

        assertNotNull(result);
        assertEquals(user.getFirstName(), result.getFirstName());
        verify(userPersistencePort, times(1)).getUser(1L);
    }

    // Test para obtener todos los usuarios
    @Test
    void getAllUsers_Success() {
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userPersistencePort.getAllUsers()).thenReturn(users);

        List<User> result = userUseCase.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userPersistencePort, times(1)).getAllUsers();
    }

    // Test para eliminar un usuario
    @Test
    void deleteUserById_Success() {
        doNothing().when(userPersistencePort).deleteUserById(1L);

        assertDoesNotThrow(() -> userUseCase.deleteUserById(1L));

        verify(userPersistencePort, times(1)).deleteUserById(1L);
    }

    // Test para validar usuario menor de edad
    @Test
    void saveUser_ShouldThrowException_WhenUnderage() {
        user.setBirthDate(LocalDate.of(2010, 1, 1)); // Menor de edad

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userUseCase.saveUserOwner(user));

        assertEquals("El usuario debe ser mayor de edad.", exception.getMessage());
    }

    @Test
    void saveUserEmployee_Success() {
        // Arrange
        User userEmployee = new User();
        userEmployee.setFirstName("Empleado");
        userEmployee.setLastName("Prueba");
        userEmployee.setDocumentNumber("123456789");
        userEmployee.setPhone("+573001234567");
        userEmployee.setBirthDate(LocalDate.of(1990, 1, 1));
        userEmployee.setEmail("empleado@plaza.com");
        userEmployee.setPassword("password123");
        userEmployee.setRoleId(2L); // Rol de empleado

        when(userPasswordEncoderPort.encodePassword(userEmployee.getPassword())).thenReturn(userEmployee.getPassword());
        doNothing().when(userPersistencePort).saveUserOwner(userEmployee);

        // Act
        userUseCase.saveUserEmployee(userEmployee);

        // Assert
        verify(userPasswordEncoderPort).encodePassword(userEmployee.getPassword());
        verify(userPersistencePort).saveUserOwner(userEmployee);
    }

    @Test
    void saveUserEmployee_InvalidRole_Failure() {
        // Arrange
        User userEmployee = new User();
        userEmployee.setFirstName("Empleado");
        userEmployee.setLastName("Prueba");
        userEmployee.setDocumentNumber("123456789");
        userEmployee.setPhone("+573001234567");
        userEmployee.setBirthDate(LocalDate.of(1990, 1, 1));
        userEmployee.setEmail("empleado@plaza.com");
        userEmployee.setPassword("password123");
        userEmployee.setRoleId(3L); // Rol inválido para este caso

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userUseCase.saveUserEmployee(userEmployee);
        });

        assertEquals("El rol especificado no existe", exception.getMessage());
        verifyNoInteractions(userPasswordEncoderPort, userPersistencePort);
    }


}