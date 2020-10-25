package com.ws.zipcode.serviceImp;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.service.ParseValidateUserInput;
import com.ws.zipcode.utilities.Utils;
import java.util.*;
import java.util.regex.Matcher;

public class ParseValidateUserInputImpl implements ParseValidateUserInput {
    @Override
    public Set<ZipcodePair> parseAndValidateUserInput(String[] args) {
        Set<ZipcodePair> zipcodePairSet = new HashSet<>();
        List<String> zipcodePairList = Arrays.asList(args);
        //Sample input for testing purpose
      //  zipcodePairSet.add(new ZipcodePair.Builder().withMaxRange(null).withMinRange(200).build());
         zipcodePairList.stream().forEach(zipcodePair -> {
            Matcher matcher = Utils.ZIP_BOUNDARY.matcher(zipcodePair.replaceAll(" ", ""));
            if (matcher.find()) {
                zipcodePairSet.add(new ZipcodePair.Builder()
                        .withMinRange(Integer.parseInt(matcher.group(1)))
                        .withMaxRange(Integer.parseInt(matcher.group(2)))
                        .build());
            }else {
                //As long as there are some working matches display error log and continue processing
                System.err.println("Please correct this inputs:  "+zipcodePair.toString());
            }
        });
        return zipcodePairSet;
    }
}
