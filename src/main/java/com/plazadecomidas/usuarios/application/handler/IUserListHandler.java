package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.users.UserListRequest;
import com.plazadecomidas.usuarios.application.dto.users.UserListResponse;

import java.util.List;

public interface IUserListHandler {

    void saveUserOwnerInList(UserListRequest userListRequest);
    void saveUserEmployeeInList(UserListRequest userListRequest);
    void saveUserClientInList(UserListRequest userListRequest);
    List<UserListResponse> getAllUserFromList();
    UserListResponse getUserFromList(Long id);
    UserListResponse getUserFromListByEmail(String email);
    void deleteUserFromList(Long id);
}
