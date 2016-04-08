package csk.mobilewebshop.tests;

import csk.mobilewebshop.dto.UserDTO;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class UserDTOTest {

    @Test
    public void shouldRaiseExceptionCauseNullUserName() {
        UserDTO u = new UserDTO(null, "aA3aA3+", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseTooShortUserName() {
        UserDTO u = new UserDTO("us", "aA3aA3+", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseNullRegistraionDate() {
        UserDTO u = new UserDTO("user", "aA3aA3+", null);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseTooShortPassWord() {
        UserDTO u = new UserDTO("user", "aA3+", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseNoSpecCharInPassWord() {
        UserDTO u = new UserDTO("user", "aaaA32", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseNoLowerCaseLetterInPassWord() {
        UserDTO u = new UserDTO("user", "AAA33.", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseNoUpperCaseLetterInPassWord() {
        UserDTO u = new UserDTO("user", "aaa33.", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseNoNumberInPassWord() {
        UserDTO u = new UserDTO("user", "aaaAAA.", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseDateViolation() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", LocalDate.MIN);
        u.setDateOfBirth(LocalDate.of(2016, Month.MARCH, 15));
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseDateOfBirthIsNotInPast() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", LocalDate.MAX);
        u.setDateOfBirth(LocalDate.of(2100, Month.MARCH, 1));
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldRaiseExceptionCauseregistrationDateIsNull() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", null);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void shouldNotRaiseException() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<UserDTO>> violations = validatior.validate(u);
        Assert.assertEquals(0, violations.size());
    }

}
