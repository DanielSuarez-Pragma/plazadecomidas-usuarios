package com.plazadecomidas.usuarios.infraestructure.input.rest;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.application.dto.UserListResponse;
import com.plazadecomidas.usuarios.application.handler.IUserListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class UserRestController {

    private final IUserListHandler userListHandler;

    @PostMapping("/saveOwner")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> saveOwner(@RequestBody UserListRequest userListRequest) {
        userListHandler.saveUserOwnerInList(userListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/saveEmployee")
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<Void> saveEmployee(@RequestBody UserListRequest userListRequest) {
        userListHandler.saveUserEmployeeInList(userListRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    public ResponseEntity<List<UserListResponse>> getAllUsersFromList() {
        return ResponseEntity.ok(userListHandler.getAllUserFromList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserListResponse> getUserFromList(@PathVariable Long id) {
        return ResponseEntity.ok(userListHandler.getUserFromList(id));
    }

    @GetMapping("/getByEmail/{email}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserListResponse> getUserFromEmail(@PathVariable String email) {
        return ResponseEntity.ok(userListHandler.getUserFromListByEmail(email));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userListHandler.deleteUserFromList(userId);
        return ResponseEntity.noContent().build();
    }
}
