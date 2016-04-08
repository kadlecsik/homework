package csk.mobilewebshop.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Payload;

@Constraint(validatedBy = PastValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PastConstraint {

    String message() default "{PastConstraint.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
