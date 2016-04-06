package csk.mobilewebshop.constraint;

import csk.mobilewebshop.dto.UserDTO;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, UserDTO> {

    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDTO user, ConstraintValidatorContext context) {
        return user.getDateOfBirth() == null || (user.getDateOfBirth().isBefore(LocalDate.now()) && user.getDateOfBirth().isBefore(user.getRegistrationDate()));
    }

}
