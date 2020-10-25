package com.ws.zipcode.utilities;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.Set;
import java.util.regex.Pattern;

public class Utils {

    public static final Comparator<ZipcodePair> ZIPCODE_PAIR_COMPARATOR=
            Comparator.comparingInt(ZipcodePair::getMinRange).thenComparingInt(ZipcodePair::getMaxRange);
    public static final Validator CONSTRAINT_VALIDATOR=  Validation.buildDefaultValidatorFactory().getValidator();
    public static final Pattern ZIP_BOUNDARY = Pattern.compile("\\[([0-9]{5}),([0-9]{5})]");

    public static void validateZipRange(Object object) {
        Set<ConstraintViolation<Object>> violations = CONSTRAINT_VALIDATOR.validate(object);
        if (violations != null && violations.size() > 0) {
            throw new IllegalArgumentException("Invalid zipcode "+ object);
        }
    }

}
