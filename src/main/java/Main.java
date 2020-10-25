import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.service.ZipCodeService;
import com.ws.zipcode.serviceImp.ParseValidateUserInputImpl;
import com.ws.zipcode.serviceImp.ZipCodeServiceImpl;


public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Given Zipcode range is empty!");
        }
        ParseValidateUserInput parseValidateArg = new ParseValidateUserInputImpl();
        ZipCodeService zipCodeService = new ZipCodeServiceImpl();
        zipCodeService.restrictedZipRange(parseValidateArg.parseAndValidateUserInput(args)).forEach(System.out::print);
    }
}
