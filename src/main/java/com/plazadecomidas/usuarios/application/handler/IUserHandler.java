package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.request.UserRequestDto;
import com.plazadecomidas.usuarios.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    Boolean existsUserById(Long id);
    List<UserResponseDto> getAllUsers();
    void deleteUserById(Long id);
}
