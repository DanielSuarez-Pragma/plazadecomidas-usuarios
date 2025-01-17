package com.plazadecomidas.usuarios.domain.constants;

public class UserErrorMessages {

    public static final String NAME_BAD_LENGTH = "Name is or too large or too short";
    public static final String NAME_REQUIRED = "First name is required";
    public static final String LASTNAME_BAD_LENGTH = "Last name is or too large or too short";
    public static final String LASTNAME_REQUIRED = "Last name is required";
    public static final String DOCUMENT_BAD_LENGTH = "Document too short.";
    public static final String DOCUMENT_REQUIRED = "Document is required.";
    public static final String PHONE_REQUIRED = "phone is required";
    public static final String PHONE_ERROR = "Phone must be a valid phone number.";
    public static final String AGE_ERROR = "The user must be adult.";
    public static final String AGE_REQUIRED = "Birth is required.";
    public static final String AGE_ERROR_PAST = "The birth date must be in the past.";
    public static final String EMAIL_ERROR = "Invalid email address.";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_ERROR = "The password must be at least 6 characters long.";
    public static final String ROLE_ERROR = "Role is required.";

    private UserErrorMessages() {
    }
}
