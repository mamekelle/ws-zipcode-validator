package com.ws.zipcode.validation;

import com.ws.zipcode.utilities.Utils;
import javax.validation.ConstraintViolationException;

/**
 *
 * @param <T> This Abstract Builder class can be used by any class for Builder design pattern
 */
public abstract class  AbstractBuilder<T> {
    /**
     *
     * @return Builder method to be implemented by the class by returning the object instance
     */
    protected abstract T buildInternal();

    /**
     *
     * @return the object after validation
     * @throws ConstraintViolationException throws if any of the bean violation occurs
     */
    public T build() throws ConstraintViolationException {
        T object = buildInternal();
        Utils.validateZipRange(object);
        return object;
    }
}
