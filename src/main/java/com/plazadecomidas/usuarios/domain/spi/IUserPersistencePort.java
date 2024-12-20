package com.plazadecomidas.usuarios.domain.spi;

import com.plazadecomidas.usuarios.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUser(User user);// Guarda un usuario
    User getUserById(Long id);//Obtener usuario por ID
    Boolean existUserById(Long id);
    List<User> getAllUsers(); // Obtiene todos los usuarios
    void deleteUserById(Long id);//Eliminar usuario por id
}
