package csk.mobilewebshop.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

public class MobileDTOTest {

    @Test
    public void shouldViolateUUIDValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "Dummy_ID", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("Dummy_ID",violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolateIdNotNullValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", null, 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolatePriceValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 0, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(0,violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolatePieceValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, -1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(-1,violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolateTypeNotNullValidation() {
        MobileDTO m = new MobileDTO(null, "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolateTypeLengthValidation() {
        MobileDTO m = new MobileDTO("IP", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("IP",violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolateManufacturerNotNullValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", null, "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals(null,violations.get(0).getInvalidValue());

    }

    @Test
    public void shouldViolateManufacturerLengthValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", "AP", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("AP",violations.get(0).getInvalidValue());
    }
    
    @Test
    public void shouldNotViolateAnyValidation() {
        MobileDTO m = new MobileDTO("Iphone 6", "Apple", "de305d54-75b4-431b-adb2-eb6b9e546014", 192000, 1);
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validatior = vf.getValidator();
        vf.close();
        List<ConstraintViolation<MobileDTO>> violations = new ArrayList(validatior.validate(m));
        Assert.assertEquals(0, violations.size());
    }

}
