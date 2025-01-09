package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.application.dto.UserListResponse;
import com.plazadecomidas.usuarios.application.mapper.RoleDtoMapper;
import com.plazadecomidas.usuarios.application.mapper.UserListRequestMapper;
import com.plazadecomidas.usuarios.application.mapper.UserListResponseMapper;
import com.plazadecomidas.usuarios.domain.api.IRoleServicePort;
import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserListHandler implements IUserListHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final UserListRequestMapper userListRequestMapper;
    private final UserListResponseMapper userListResponseMapper;
    private final RoleDtoMapper roleDtoMapper;

    public UserListHandler(IUserServicePort userServicePort, IRoleServicePort roleServicePort, UserListRequestMapper userListRequestMapper, UserListResponseMapper userListResponseMapper, RoleDtoMapper roleDtoMapper) {
        this.userServicePort = userServicePort;
        this.roleServicePort = roleServicePort;
        this.userListRequestMapper = userListRequestMapper;
        this.userListResponseMapper = userListResponseMapper;
        this.roleDtoMapper = roleDtoMapper;
    }

    @Override
    public void saveUserOwnerInList(UserListRequest userListRequest) {
        // Convertir el DTO en un objeto del dominio
        User user = userListRequestMapper.toUser(userListRequest);
        // Asignar el roleId directamente desde el request
        user.setRoleId(userListRequest.getRole());
        userServicePort.saveUserOwner(user);
    }

    @Override
    public void saveUserEmployeeInList(UserListRequest userListRequest) {
        User user = userListRequestMapper.toUser(userListRequest);
        user.setRoleId(userListRequest.getRole());
        userServicePort.saveUserEmployee(user);
    }

    @Override
    public List<UserListResponse> getAllUserFromList() {
        return userListResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public UserListResponse getUserFromList(Long id) {
        User user = userServicePort.getUser(id);
        return userListResponseMapper.toResponse(user, roleDtoMapper.toDto(roleServicePort.getRole(user.getRoleId())));
    }

    @Override
    public UserListResponse getUserFromListByEmail(String email) {
        User user = userServicePort.getUserByEmail(email);
        return userListResponseMapper.toResponse(user, roleDtoMapper.toDto(roleServicePort.getRole(user.getRoleId())));
    }

    @Override
    public void deleteUserFromList(Long id) {
        userServicePort.deleteUserById(id);
    }
}
