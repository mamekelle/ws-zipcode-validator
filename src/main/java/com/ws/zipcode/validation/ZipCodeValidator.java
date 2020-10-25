package com.ws.zipcode.validation;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.utilities.Utils;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class ZipCodeValidator {

    public void validateZipRange(ZipcodePair zipcodePair) {
        Set<ConstraintViolation<ZipcodePair>> violations = Utils.CONSTRAINT_VALIDATOR.validate(zipcodePair);
        if (violations != null && violations.size() > 0) {
            //Will add custom exception
            throw new IllegalArgumentException("Invalid zipcode "+ zipcodePair);
        }
    }

}
