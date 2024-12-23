package com.plazadecomidas.usuarios.domain.api;

import com.plazadecomidas.usuarios.domain.model.User;

import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);// Guarda un usuario
    User getUser(Long id);//Obtener usuario por ID
    List<User> getAllUsers(); // Obtiene todos los usuarios
    void deleteUserById(Long id);//Eliminar usuario por id
}