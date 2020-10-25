package com.ws.zipcode.serviceTest;
import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.serviceImp.ParseValidateUserInputImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;


public class ParseValidateUserInputTest {

    private ParseValidateUserInput parseValidateUserInput;

    @Before
    public void setup(){
        parseValidateUserInput= new ParseValidateUserInputImpl();
    }
    @Test
    public void parseValidateUserInputCreated(){
        assertNotNull(parseValidateUserInput);
    }

    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputNullArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(null);
    }
    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputEmptyArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(new String[]{});
    }
    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputInvalidLetterArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(new String[]{"[abc,def]"});
    }
    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputOninvalidNegativeArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(new String[]{"[-91,-90]"});
    }
    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputOninvalidSingleArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(new String[]{"[999999190]"});
    }
    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputOninvalidDigitArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(new String[]{"[999999190,00000]"});
    }
    @Test()
    public void parseValidateUserInputOnValidDigitArgument() throws ZipCodeException {
        Set<ZipcodePair>  result=parseValidateUserInput.parseAndValidateUserInput(new String[]{"[12345,67891]"});
    assertEquals(result.size(),1);
    }

    @Test()
    public void parseValidateUserInputOnMultipliedDigitArgument() throws ZipCodeException {
        String str[] = new String[]{"[12345,23456]","[34567,45678]", "[56789,67891]"};
        Set<ZipcodePair>  result=parseValidateUserInput.parseAndValidateUserInput(str);
        assertEquals(result.size(),3);
    }

    @Test(expected = ZipCodeException.class)
    public void parseValidateUserInputOnEmptyArgument() throws ZipCodeException {
        parseValidateUserInput.parseAndValidateUserInput(new String[]{});
    }

//    @Test(expected = ZipCodeException.class)
//    public void parseValidateUserInputOnInvalidArgument() throws ZipCodeException {
//        parseValidateUserInput.parseAndValidateUserInput(new String[]{"[5678912,6789133]"});
//    }
}
