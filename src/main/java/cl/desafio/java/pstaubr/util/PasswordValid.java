package cl.desafio.java.pstaubr.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Pablo Staub R
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValid {
    String message() default "Campo: password formato incorrecto...";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
