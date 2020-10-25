package com.ws.zipcode.validation;

import com.ws.zipcode.utilities.Utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

public abstract class  AbstractBuilder<T> {

    protected abstract T buildInternal();

    public T build() throws ConstraintViolationException {

        T object = buildInternal();

        Set<ConstraintViolation<T>> violations = Utils.CONSTRAINT_VALIDATOR.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }
        return object;
    }
}
