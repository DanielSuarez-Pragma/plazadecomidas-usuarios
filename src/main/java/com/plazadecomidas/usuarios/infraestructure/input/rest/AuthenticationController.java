package com.plazadecomidas.usuarios.infraestructure.input.rest;

import com.plazadecomidas.usuarios.application.dto.auth.AuthLoginRequestDto;
import com.plazadecomidas.usuarios.application.dto.auth.AuthResponseDto;
import com.plazadecomidas.usuarios.application.dto.users.UserListRequest;
import com.plazadecomidas.usuarios.application.handler.IAuthHandler;
import com.plazadecomidas.usuarios.application.handler.IUserListHandler;
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

    private final IUserListHandler userListHandler;
    private final IAuthHandler authHandler;

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthLoginRequestDto userRequest){
        return new ResponseEntity<>(authHandler.loginUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponseDto> saveClient(@RequestBody UserListRequest userListRequest) {
        userListHandler.saveUserClientInList(userListRequest);
        return new ResponseEntity<>(authHandler.registerClient(userListRequest.getEmail(), userListRequest.getPassword()), HttpStatus.OK);
    }

}
