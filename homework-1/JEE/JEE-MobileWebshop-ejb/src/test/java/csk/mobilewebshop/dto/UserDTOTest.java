package csk.mobilewebshop.dto;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class UserDTOTest {

    @Test
    public void shouldViolateUserNameNotNullValidation() {
        UserDTO u = new UserDTO(null, "aA3aA3+", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateUserNameLengthValidation() {
        UserDTO u = new UserDTO("us", "aA3aA3+", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("us",violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateRegistraionDateNotNullValidation() {
        UserDTO u = new UserDTO("user", "aA3aA3+", null);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolatePassWordLengthValidation() {
        UserDTO u = new UserDTO("user", "aA3+", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aA3+",violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateSpecCharInPassWordValidation() {
        UserDTO u = new UserDTO("user", "aaaA32", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aaaA32",violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateLowerCaseLetterInPassWordValidation() {
        UserDTO u = new UserDTO("user", "AAA33.", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("AAA33.",violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateUpperCaseLetterInPassWordValidation() {
        UserDTO u = new UserDTO("user", "aaa33.", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aaa33.",violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateNumberInPassWordValidation() {
        UserDTO u = new UserDTO("user", "aaaAAA.", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("aaaAAA.",violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateDateOfBirthConstraintValidation() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", LocalDate.MIN);
        u.setDateOfBirth(LocalDate.of(2016, Month.MARCH, 15));
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(u,violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateDateOfBirthIsNotInPastValidation() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", LocalDate.MAX);
        u.setDateOfBirth(LocalDate.of(2100, Month.MARCH, 1));
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(LocalDate.of(2100, Month.MARCH, 1),violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldViolateRegistrationDateIsNotNullValidation() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", null);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());
    }

    @Test
    public void shouldNotViolateAnyValidation() {
        UserDTO u = new UserDTO("user", "aaaAAA.3", LocalDate.now());
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<UserDTO>> violations = new ArrayList(validatior.validate(u));
        Assert.assertEquals(0, violations.size());
    }

}
