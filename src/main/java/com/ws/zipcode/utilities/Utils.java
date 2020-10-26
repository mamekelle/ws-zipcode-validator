package com.ws.zipcode.utilities;

import com.ws.zipcode.domain.ZipcodePair;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Utils {

    private final static Logger logger = Logger.getLogger(Utils.class.getName());
    // For comparing and storing in sorted order in both cases using first the minRange and then maxRange
    public static final Comparator<ZipcodePair> ZIPCODE_PAIR_COMPARATOR=
            Comparator.comparingInt(ZipcodePair::getMinRange).thenComparingInt(ZipcodePair::getMaxRange);
    //JavaFx Constraint validator constant
    public static final Validator CONSTRAINT_VALIDATOR=  Validation.buildDefaultValidatorFactory().getValidator();
    //The pattern to identify the given input value is 5 digit number
    public static final Pattern ZIP_BOUNDARY = Pattern.compile("\\[([0-9]{5}),([0-9]{5})]");

    /**
     * To identiy the given object is valid object based on the JavaFx Constraint validator
     * @param object zipcode given input
     */
    public static void validateZipRange(Object object) {
        Set<ConstraintViolation<Object>> violations = CONSTRAINT_VALIDATOR.validate(object);
        if ( violations.size() > 0) {
            logger.severe("The given zipcode is not valid");
            throw new IllegalArgumentException("Invalid zipcode "+ object);
        }
    }
}
