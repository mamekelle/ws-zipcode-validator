package com.ws.zipcode.validation;

import com.ws.zipcode.utilities.Utils;
import javax.validation.ConstraintViolationException;

public abstract class  AbstractBuilder<T> {

    protected abstract T buildInternal();

    public T build() throws ConstraintViolationException {
        T object = buildInternal();
        Utils.validateZipRange(object);
        return object;
    }
}
