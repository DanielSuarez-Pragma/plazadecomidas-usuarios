package com.plazadecomidas.usuarios.domain.usecase;

import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.exception.NoDataException;
import com.plazadecomidas.usuarios.domain.exception.ValidationUserException;
import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static com.plazadecomidas.usuarios.domain.constants.ErrorMessages.NO_DATA_FOUND;
import static com.plazadecomidas.usuarios.domain.constants.ErrorMessages.ROLE_NOT_FOUND;
import static com.plazadecomidas.usuarios.domain.constants.Roles.*;
import static com.plazadecomidas.usuarios.domain.constants.UserErrorMessages.*;
import static com.plazadecomidas.usuarios.domain.constants.UserVariablesConstants.*;

@AllArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;

    @Override
    public void saveUserOwner(User user) {
        user.setRole(getRole(OWNER));
        validateUserData(user);
        user.setPassword(userPasswordEncoderPort.encodePassword(user.getPassword()));
        userPersistencePort.saveUserOwner(user);
    }

    @Override
    public void saveUserEmployee(User user) {
        user.setRole(getRole(EMPLOYEE));
        validateUserData(user);
        user.setPassword(userPasswordEncoderPort.encodePassword(user.getPassword()));
        userPersistencePort.saveUserEmployee(user);
    }

    @Override
    public void saveUserClient(User user) {
        user.setRole(getRole(CLIENT));
        validateUserData(user);
        user.setPassword(userPasswordEncoderPort.encodePassword(user.getPassword()));
        userPersistencePort.saveUserClient(user);
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userPersistencePort.getAllUsers();
        if (users.isEmpty()) {
            throw new NoDataException(NO_DATA_FOUND);
        }
        return users;
    }

    @Override
    public void deleteUserById(Long id) {
        userPersistencePort.deleteUserById(id);
    }

    private Role getRole(String roleName) {
        Role role = rolePersistencePort.findRoleByName(roleName);
        if (role == null) {
            throw new NoDataException(ROLE_NOT_FOUND);
        }
        return role;
    }

    private void validateUserData(User user) {
        // Validar nombre
        if (user.getFirstName() == null || user.getFirstName().length() < MIN_NAME_LENGTH) {
            throw new ValidationUserException(NAME_BAD_LENGTH);
        }

        // Validar apellido
        if (user.getLastName() == null || user.getLastName().length() < MIN_LASTNAME_LENGTH) {
            throw new ValidationUserException(LASTNAME_BAD_LENGTH);
        }
        // Validar número de documento y que tiene solo numeros
        if (user.getDocumentNumber() == null || user.getDocumentNumber().length() < MIN_DOCUMENT_LENGTH ||!user.getDocumentNumber().matches(DOCUMENT_REGEX)) {
            throw new ValidationUserException(DOCUMENT_BAD_LENGTH);
        }

        // Validar número de teléfono (10 dígitos o +57 seguido de 10 dígitos)
        if (user.getPhone() == null || !user.getPhone().matches(PHONE_REGEX)) {
            throw new ValidationUserException(PHONE_ERROR);
        }

        // Validar fecha de nacimiento (mayor de edad)
        if (user.getBirthDate() == null || !isAdult(user.getBirthDate())) {
            throw new ValidationUserException(AGE_ERROR);
        }

        // Validar correo electrónico
        if (user.getEmail() == null || !user.getEmail().matches(EMAIL_REGEX)) {
            throw new ValidationUserException(EMAIL_ERROR);
        }

        // Validar contraseña
        if (user.getPassword() == null || user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new ValidationUserException(PASSWORD_ERROR);
        }

        // Validar rol
        if (user.getRole() == null) {
            throw new ValidationUserException(ROLE_ERROR);
        }
    }

    //validar si el usuario es mayor de edad
    private boolean isAdult(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears() >= MIN_AGE;
    }

}