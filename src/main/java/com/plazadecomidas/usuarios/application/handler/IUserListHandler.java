package com.plazadecomidas.usuarios.application.handler;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.application.dto.UserListResponse;

import java.util.List;

public interface IUserListHandler {

    void saveUserInList(UserListRequest userListRequest);
    List<UserListResponse> getAllUserFromList();
    UserListResponse getUserFromList(Long id);
    void deleteUserFromList(Long id);

}
