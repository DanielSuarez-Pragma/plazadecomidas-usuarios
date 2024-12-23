package com.plazadecomidas.usuarios.domain.spi;

import com.plazadecomidas.usuarios.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUser(User user);// Guarda un usuario
    List<User> getAllUsers(); // Obtiene todos los usuarios
    User getUser(Long id);//Obtener usuario por ID
    void deleteUserById(Long id);//Eliminar usuario por id
}
