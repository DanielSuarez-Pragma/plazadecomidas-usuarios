package com.plazadecomidas.usuarios.infraestructure.input.rest;

import com.plazadecomidas.usuarios.application.handler.IUserListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleRestController {
    private IUserListHandler userListHandler;
}
