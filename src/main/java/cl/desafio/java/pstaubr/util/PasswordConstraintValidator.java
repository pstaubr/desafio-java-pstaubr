package cl.desafio.java.pstaubr.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pablo Staub R
 */
public class PasswordConstraintValidator implements ConstraintValidator<PasswordValid, String> {

    /**
     * Retorna true o false formato contraseña
     *
     * @param password object to validate
     * @param context  context in which the constraint is evaluated
     * @return
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        /**
         *  Ejemplo formato minimo 3 caracteres max 20
         *  Una letra mayuscula
         *  Letras minusculas
         */
        Pattern p = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{3,20}$");

        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);
        return m.matches();
    }
}