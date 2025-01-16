package com.plazadecomidas.usuarios.domain.constants;

public class UserErrorMessages {

    public static final String NAME_BAD_LENGTH = "El nombre debe ser mas largo.";
    public static final String LASTNAME_BAD_LENGTH = "El apellido debe ser mas largo.";
    public static final String DOCUMENT_BAD_LENGTH = "El documento debe ser tener mas caracteres.";
    public static final String PHONE_ERROR = "El número de celular debe ser de 10 dígitos o incluir el prefijo +57 seguido de 10 dígitos.";
    public static final String AGE_ERROR = "El usuario debe ser mayor de edad.";
    public static final String EMAIL_ERROR = "El correo electrónico no es válido.";
    public static final String PASSWORD_ERROR = "La contraseña debe tener al menos 6 caracteres.";
    public static final String ROL_ERROR = "El rol es obligatorio.";

    private UserErrorMessages() {
    }
}
