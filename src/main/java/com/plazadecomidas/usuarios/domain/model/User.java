package com.plazadecomidas.usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Long roleId; // Relación con Rol

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
