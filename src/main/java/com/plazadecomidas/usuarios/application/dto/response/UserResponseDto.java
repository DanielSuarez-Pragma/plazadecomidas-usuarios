package com.plazadecomidas.usuarios.application.dto.response;

import com.plazadecomidas.usuarios.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String email;
    private Role roles;
}
