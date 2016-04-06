package csk.mobilewebshop.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Payload;

/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOfBirthConstraint {

    String message() default "{DateOfBirth.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
