package com.ws.zipcode.exception;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.serviceImp.ParseValidateUserInputImpl;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Set;

public class ExceptionTest {

    @Test(expected = ZipCodeException.class)
    public void myTest() throws ZipCodeException {
        ParseValidateUserInput parseValidateUserInput = new ParseValidateUserInputImpl();
        Set<ZipcodePair> result = parseValidateUserInput.parseAndValidateUserInput(new String[]{"[12345,123]"});
        assertNotNull(result);
    }
}
