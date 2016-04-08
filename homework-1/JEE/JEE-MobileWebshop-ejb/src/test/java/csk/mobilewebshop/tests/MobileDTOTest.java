package csk.mobilewebshop.tests;

import csk.mobilewebshop.dto.MobileDTO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class MobileDTOTest {

    @Test
    public void shouldRaiseExceptionCauseInvalidId() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "Dummy_ID", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseNullId() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", null, 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseInvalidPrice() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 0, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseInvalidPiece() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, -1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseNullType() {
        MobileDTO m = new MobileDTO(null, "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseTooShortType() {
        MobileDTO m = new MobileDTO("IP", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseNullManufacturer() {
        MobileDTO m = new MobileDTO("Iphone 6", null, "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());

    }

    @Test
    public void shouldRaiseExceptionCauseTooShortManufacturer() {
        MobileDTO m = new MobileDTO("Iphone 6", "AP", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(1, violations.size());
    }
    
    @Test
    public void shouldNotRaiseException() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        Set<ConstraintViolation<MobileDTO>> violations = validatior.validate(m);
        Assert.assertEquals(0, violations.size());
    }

}
