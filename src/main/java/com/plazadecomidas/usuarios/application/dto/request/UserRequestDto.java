package com.plazadecomidas.usuarios.application.dto.request;

import com.plazadecomidas.usuarios.application.validation.IsAdult;
import com.plazadecomidas.usuarios.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String name;
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
    private String lastName;
    @NotBlank(message = "El documento es obligatorio")
    @Size(min = 5, message = "El documento debe tener al menos 5 numeros")
    private String documentNumber;
    @NotBlank(message = "El telefono es obligatorio")
    @Pattern(regexp = "^(\\+57\\d{10}|\\d{10})$", message = "El número de celular debe contener exactamente 10 dígitos o el prefijo '+57' seguido de 10 dígitos")
    private String phone;
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @IsAdult(message = "Debe ser mayor de edad")
    private String birthDate; // Formato ISO-8601 como String ("YYYY-MM-DD")
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser valido")
    private String email;
    @NotBlank(message = "La clave es requerida")
    private String password;
    private Long roleId;
}
