package com.plazadecomidas.usuarios.infraestructure.input.rest;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.application.dto.UserListResponse;
import com.plazadecomidas.usuarios.application.handler.IUserListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserListHandler userListHandler;

    @PostMapping("/saveUser")
    public ResponseEntity<Void> saveUser(@RequestBody UserListRequest userListRequest) {
        userListHandler.saveUserInList(userListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserListResponse>> getAllUsersFromList() {
        return ResponseEntity.ok(userListHandler.getAllUserFromList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserListResponse> getUserFromList(@PathVariable Long id) {
        return ResponseEntity.ok(userListHandler.getUserFromList(id));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userListHandler.deleteUserFromList(userId);
        return ResponseEntity.noContent().build();
    }
}
