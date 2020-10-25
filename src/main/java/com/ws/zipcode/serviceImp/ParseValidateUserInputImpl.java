package com.ws.zipcode.serviceImp;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.utilities.Utils;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;

public class ParseValidateUserInputImpl implements ParseValidateUserInput {
    private final static Logger logger = Logger.getLogger(Utils.class.getName());

    @Override
    public Set<ZipcodePair> parseAndValidateUserInput(String[] args) throws ZipCodeException {
       if(args==null||args.length<1){
           throw new ZipCodeException("Given Zipcode range is empty!");
       }
        Set<ZipcodePair> zipcodePairSet = new HashSet<>();
        List<String> zipcodePairList = Arrays.asList(args);
        //Sample input for testing purpose
      //  zipcodePairSet.add(new ZipcodePair.Builder().withMaxRange(null).withMinRange(200).build());
        for (String zipcodePair : zipcodePairList) {
            Matcher matcher = Utils.ZIP_BOUNDARY.matcher(zipcodePair.replaceAll(" ", ""));
            if (matcher.find()) {
                zipcodePairSet.add(new ZipcodePair.Builder()
                        .withMinRange(Integer.parseInt(matcher.group(1)))
                        .withMaxRange(Integer.parseInt(matcher.group(2)))
                        .build());
            } else throw new ZipCodeException("Please correct this inputs:  ");
        }
        return zipcodePairSet;
    }
}
