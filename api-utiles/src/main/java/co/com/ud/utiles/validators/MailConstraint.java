package co.com.ud.utiles.validators;

import co.com.ud.utiles.validators.impl.MailConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MailConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MailConstraint {

    String message() default "Correo invalido";
    String campo() default "Mail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
