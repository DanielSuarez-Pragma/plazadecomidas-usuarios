package com.plazadecomidas.usuarios.domain.spi;

import com.plazadecomidas.usuarios.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUserOwner(User user);// Guarda un usuario
    void saveUserEmployee(User user);
    void saveUserClient(User user);
    List<User> getAllUsers(); // Obtiene todos los usuarios
    User getUser(Long id);//Obtener usuario por ID
    User getUserByEmail(String email);
    void deleteUserById(Long id);//Eliminar usuario por id
}
