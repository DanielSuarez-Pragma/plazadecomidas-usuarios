package com.plazadecomidas.usuarios.domain.constants;

public class UserVariablesConstants {

    public static final Integer MIN_NAME_LENGTH = 3;
    public static final Integer MAX_NAME_LENGTH = 3;

    public static final Integer MIN_LASTNAME_LENGTH = 3;
    public static final Integer MAX_LASTNAME_LENGTH = 20;

    public static final Integer MIN_DOCUMENT_LENGTH = 6;
    public static final Integer MAX_DOCUMENT_LENGTH = 20;

    public static final Integer MIM_PHONE_LENGTH = 10;
    public static final Integer MAX_PHONE_LENGTH = 13;

    public static final Integer MIN_PASSWORD_LENGTH = 6;
    public static final Integer MIN_AGE = 18;
    public static final String DOCUMENT_REGEX = "\\d+";
    public static final String PHONE_REGEX = "^(\\+57\\d{10}|\\d{10})$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    private UserVariablesConstants() {
    }
}
