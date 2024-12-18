package cl.desafio.java.pstaubr.util;

/**
 * @author Pablo Staub R
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants class");
    }

    public static final String EMPTY = "";
    public static final String VALUE_BEARER = "Bearer";

    public static final String EXIST_EMAIL = "Email ya existe en nuestro registros...";
    public static final String USER_OK = "Usuario registrado con éxtio";
    public static final String JWT_AUTHORIZATION = "Authorization";

    public static final String JWT_EARER = "Bearer ";

    public static final String CANNOT_USER_AUTHENTICATION = "Cannot set user authentication: {}";

    public static final String GENERIC_MSG = "Mensaje error: ";

    public static final String ROLE_USER = "ROLE_USER";
    public static final String VALIDATE_PASSWORD = "Password formato incorrecto";

    public static final String USER_NOT_FOUND_TOKEN = "User Not Found with token: ";

}