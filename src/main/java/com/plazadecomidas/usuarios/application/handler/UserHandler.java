package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.request.UserRequestDto;
import com.plazadecomidas.usuarios.application.dto.response.UserResponseDto;
import com.plazadecomidas.usuarios.application.mapper.IUserRequestMapper;
import com.plazadecomidas.usuarios.application.mapper.IUserResponseMapper;
import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        User user = userRequestMapper.toUser(userRequestDto);
        userServicePort.saveUser(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userResponseMapper.toResponse(userServicePort.getUserById(id));
    }


    @Override
    public Boolean existsUserById(Long id) {
        return userServicePort.existUserById(id);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public void deleteUserById(Long id) {
        userServicePort.deleteUserById(id);
    }
}
