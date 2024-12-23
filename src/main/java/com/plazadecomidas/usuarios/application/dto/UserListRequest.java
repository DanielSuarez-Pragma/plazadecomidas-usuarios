package com.plazadecomidas.usuarios.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserListRequest {
    @JsonProperty("name")
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    @JsonProperty("roleId")
    private Long role;
}
