package com.plazadecomidas.usuarios.infraestructure.input.rest;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.application.handler.IUserListHandler;
import com.plazadecomidas.usuarios.infraestructure.security.UserDetailServiceImpl;
import com.plazadecomidas.usuarios.infraestructure.security.dto.AuthLoginRequest;
import com.plazadecomidas.usuarios.infraestructure.security.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@PreAuthorize("permitAll()")
public class AuthenticationController {

    private UserDetailServiceImpl userDetailService;
    private final IUserListHandler userListHandler;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> saveClient(@RequestBody UserListRequest userListRequest) {
        userListHandler.saveUserClientInList(userListRequest);
        return new ResponseEntity<>(this.userDetailService.registerClient(userListRequest.getEmail(), userListRequest.getPassword()), HttpStatus.OK);
    }

}
