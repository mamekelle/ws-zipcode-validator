package com.ws.zipcode.serviceImp;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.utilities.Utils;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Class used to parse, validate, and create distinct ZipcodePair ranges
 */
public class ParseValidateUserInputImpl implements ParseValidateUserInput {
    private final static Logger logger = Logger.getLogger(Utils.class.getName());

    /**
     * Accepts the args from the main class and validates with the given pattern from utils class
     * then creates a unique set
     * @param args
     * @return
     * @throws ZipCodeException
     */
    @Override
    public Set<ZipcodePair> parseAndValidateUserInput(String[] args) throws ZipCodeException {
       if(args==null||args.length<1){
           logger.severe("The given input range is empty or null");
           throw new ZipCodeException("Given Zipcode range is empty!");
       }
        Set<ZipcodePair> zipcodePairSet = new HashSet<>();
        List<String> zipcodePairList = Arrays.asList(args);
        //Sample input for testing purpose
      //zipcodePairSet.add(new ZipcodePair.Builder().withMaxRange(null).withMinRange(200).build());
        for (String zipcodePair : zipcodePairList) {
            //Assumed space separated input ranges[12345,23456] [34567,45678]
            Matcher matcher = Utils.ZIP_BOUNDARY.matcher(zipcodePair.replaceAll(" ", ""));
            if (matcher.find()) {
                zipcodePairSet.add(new ZipcodePair.Builder()
                        .withMinRange(Integer.parseInt(matcher.group(1)))
                        .withMaxRange(Integer.parseInt(matcher.group(2)))
                        .build());
            } else {
                logger.severe("Please the correct inputs for the following Zipcode range: "+zipcodePair.toString());
                throw new ZipCodeException("Please correct these inputs:  " + zipcodePair.toString());
            }
        }
        logger.info("Set of all the valid and unique Zipcode pair ranges: "+zipcodePairSet.toString());
        return zipcodePairSet;
    }
}
