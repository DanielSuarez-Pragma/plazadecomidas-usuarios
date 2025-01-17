package com.plazadecomidas.usuarios.application.dto.users;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.plazadecomidas.usuarios.domain.constants.UserErrorMessages.*;
import static com.plazadecomidas.usuarios.domain.constants.UserVariablesConstants.*;

@Getter
@Setter
public class UserListRequest {

    @NotBlank(message = NAME_REQUIRED)
    @Size(min = 3, max = 20, message = NAME_BAD_LENGTH)
    private String firstName;

    @NotBlank(message = LASTNAME_REQUIRED)
    @Size(min = 3, max = 20, message = LASTNAME_BAD_LENGTH)
    private String lastName;

    @NotBlank(message = DOCUMENT_REQUIRED)
    @Size(min = 6, max = 20, message = DOCUMENT_BAD_LENGTH)
    private String documentNumber;

    @NotBlank(message = PHONE_REQUIRED)
    @Size(min = 10,max = 13, message = PHONE_ERROR)
    @Pattern(regexp = PHONE_REGEX, message = PHONE_ERROR)
    private String phone;

    @NotNull(message = AGE_REQUIRED)
    @Past(message = AGE_ERROR_PAST)
    private LocalDate birthDate;

    @Email(message = EMAIL_ERROR)
    private String email;

    @NotBlank(message = PASSWORD_REQUIRED)
    @Size(min = 6, message = PASSWORD_ERROR)
    private String password;
}
