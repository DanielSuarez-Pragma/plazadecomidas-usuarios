package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.application.dto.UserListResponse;
import com.plazadecomidas.usuarios.application.mapper.UserListRequestMapper;
import com.plazadecomidas.usuarios.application.mapper.UserListResponseMapper;
import com.plazadecomidas.usuarios.domain.api.IUserServicePort;
import com.plazadecomidas.usuarios.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserListHandler implements IUserListHandler {

    private final IUserServicePort userServicePort;
    private final UserListRequestMapper userListRequestMapper;
    private final UserListResponseMapper userListResponseMapper;

    @Override
    public void saveUserOwnerInList(UserListRequest userListRequest) {
        User user = userListRequestMapper.toUser(userListRequest);
        userServicePort.saveUserOwner(user);
    }

    @Override
    public void saveUserEmployeeInList(UserListRequest userListRequest) {
        User user = userListRequestMapper.toUser(userListRequest);
        userServicePort.saveUserEmployee(user);
    }

    @Override
    public void saveUserClientInList(UserListRequest userListRequest) {
        User user = userListRequestMapper.toUser(userListRequest);
        userServicePort.saveUserClient(user);
    }

    @Override
    public List<UserListResponse> getAllUserFromList() {
        return userListResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public UserListResponse getUserFromList(Long id) {
        User user = userServicePort.getUser(id);
        return userListResponseMapper.toResponse(user);
    }

    @Override
    public UserListResponse getUserFromListByEmail(String email) {
        User user = userServicePort.getUserByEmail(email);
        return userListResponseMapper.toResponse(user);
    }

    @Override
    public void deleteUserFromList(Long id) {
        userServicePort.deleteUserById(id);
    }
}
