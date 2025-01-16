package com.plazadecomidas.usuarios.domain.api;

import com.plazadecomidas.usuarios.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    void saveUserOwner(User user);// Guarda un usuario owner
    void saveUserEmployee(User user);
    void saveUserClient(User user);
    User getUser(Long id);//Obtener usuario por ID
    User getUserByEmail(String email);
    List<User> getAllUsers(); // Obtiene todos los usuarios
    void deleteUserById(Long id);//Eliminar usuario por id
}
