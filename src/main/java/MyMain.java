import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.service.ZipCodeService;
import com.ws.zipcode.serviceImp.ParseValidateUserInputImpl;
import com.ws.zipcode.serviceImp.ZipCodeServiceImpl;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Main class where the application starts and the user input can be passed from the program arguments in the format of:
 * [12345,23456] [12325,63456] ...[minRange,maxRange] Please insert space separated pairs the filtering on the match
 * expects space separated input
 */
public class MyMain {
    private final static Logger logger = Logger.getLogger(MyMain.class.getName());

    /**
     *Main method to start the application and accept the args and validate if not valid throws the exceptions
     * @param args inputs from user
     * @throws ZipCodeException validate with custom exception
     * @throws IllegalAccessException validate with built in exception
     */
    public static void main(String[] args) throws ZipCodeException, IllegalAccessException {
        if (args == null || args.length == 0) {
            logger.severe("Please provide a valid Zipcode range");
            throw new IllegalArgumentException("Given Zipcode range is empty!");
        }
        ParseValidateUserInput parseValidateArg = new ParseValidateUserInputImpl();
        ZipCodeService zipCodeService = new ZipCodeServiceImpl();
        logger.info("The given inputs are: "+ Arrays.toString(args));
        zipCodeService.restrictedZipRange(parseValidateArg.parseAndValidateUserInput(args)).forEach(System.out::print);
    }
}
