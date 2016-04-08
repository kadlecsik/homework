package csk.mobilewebshop.constraint;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PastValidator implements ConstraintValidator<PastConstraint, LocalDate> {

    @Override
    public void initialize(PastConstraint constraintAnnotation) {
            //Empty
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return date == null || date.isBefore(LocalDate.now());
    }

}
