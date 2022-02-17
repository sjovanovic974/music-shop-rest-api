package sasa.jovanovic.musicshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sasa.jovanovic.musicshop.models.ProductCategory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTests {

    @Test
    @DisplayName("Should pass validation checks")
    void validateInputPass(){

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        ProductCategory dvd = new ProductCategory();
        dvd.setId(1L);
        dvd.setCategoryName("DVD");

        Set<ConstraintViolation<ProductCategory>> violations = validator.validate(dvd);

        assertTrue(violations.isEmpty());
    }
    @Test
    @DisplayName("Should fail validation checks")
    void validateInputFail(){

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        ProductCategory dvd = new ProductCategory();
        Set<ConstraintViolation<ProductCategory>> violations = validator.validate(dvd);

        assertFalse(violations.isEmpty());
    }
}
