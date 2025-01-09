package com.plazadecomidas.usuarios.domain.usecase;

import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.domain.spi.IUserPasswordEncoderPort;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IUserPasswordEncoderPort userPasswordEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPasswordEncoderPort userPasswordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.userPasswordEncoderPort = userPasswordEncoderPort;
    }

    @Override
    public void saveUserOwner(User user) {
        if(user.getRoleId() == 1){
            validateUserData(user);
            user.setPassword(userPasswordEncoderPort.encodePassword(user.getPassword()));
            userPersistencePort.saveUserOwner(user);
        }else{
            throw new IllegalArgumentException("El rol especificado no existe");
        }

    }

    @Override
    public void saveUserEmployee(User user) {
        if(user.getRoleId() == 2){
            validateUserData(user);
            user.setPassword(userPasswordEncoderPort.encodePassword(user.getPassword()));
            userPersistencePort.saveUserOwner(user);
        }else{
            throw new IllegalArgumentException("El rol especificado no existe");
        }
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
        return userPersistencePort.getAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        userPersistencePort.deleteUserById(id);
    }

    private void validateUserData(User user) {
        // Validar nombre
        if (user.getFirstName() == null || user.getFirstName().length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres.");
        }

        // Validar apellido
        if (user.getLastName() == null || user.getLastName().length() < 3) {
            throw new IllegalArgumentException("El apellido debe tener al menos 3 caracteres.");
        }
        String documentRegex = "\\d+";
        // Validar número de documento y que tiene solo numeros
        if (user.getDocumentNumber() == null || user.getDocumentNumber().length() < 5 ||!user.getDocumentNumber().matches(documentRegex)) {
            throw new IllegalArgumentException("El número de documento debe tener al menos 5 dígitos solo numeros.");
        }

        // Validar número de teléfono (10 dígitos o +57 seguido de 10 dígitos)
        String phoneRegex = "^(\\+57\\d{10}|\\d{10})$";
        if (user.getPhone() == null || !user.getPhone().matches(phoneRegex)) {
            throw new IllegalArgumentException("El número de celular debe ser de 10 dígitos o incluir el prefijo +57 seguido de 10 dígitos.");
        }

        // Validar fecha de nacimiento (mayor de edad)
        if (user.getBirthDate() == null || !isAdult(user.getBirthDate())) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
        }

        // Validar correo electrónico
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }

        // Validar contraseña
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }

        // Validar rol
        if (user.getRoleId() == null) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }
    }

    //validar si el usuario es mayor de edad
    private boolean isAdult(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears() >= 18;
    }

}