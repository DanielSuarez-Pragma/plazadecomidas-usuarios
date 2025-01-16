package com.plazadecomidas.usuarios.domain.constants;

public class UserVariablesConstants {

    public static final Integer MINIMUM_NAME_LENGTH = 3;
    public static final Integer MINIMUM_LASTNAME_LENGTH = 3;
    public static final Integer MINIMUM_DOCUMENT_LENGTH = 6;
    public static final Integer MINIMUM_PASSWORD_LENGTH = 6;
    public static final Integer MINIMUM_AGE = 18;
    public static final String DOCUMENT_REGEX = "\\d+";
    public static final String PHONE_REGEX = "^(\\+57\\d{10}|\\d{10})$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    private UserVariablesConstants() {
    }
}
