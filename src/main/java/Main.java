import com.ws.zipcode.service.ParseValidateArg;
import com.ws.zipcode.service.ZipCodeService;
import com.ws.zipcode.serviceImp.ParseValidateArgImpl;
import com.ws.zipcode.serviceImp.ZipCodeServiceImpl;


public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Given Zipcode range is empty!");
        }
        ParseValidateArg parseValidateArg = new ParseValidateArgImpl();
        ZipCodeService zipCodeService = new ZipCodeServiceImpl();
        zipCodeService.restrictedZipRange(parseValidateArg.parseAndValidate(args)).forEach(System.out::print);
    }
}
