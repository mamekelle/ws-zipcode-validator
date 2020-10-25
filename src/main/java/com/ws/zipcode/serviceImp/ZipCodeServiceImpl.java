package com.ws.zipcode.serviceImp;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ZipCodeService;

import java.util.*;
import java.util.logging.Logger;

/**
 * This class implements to ZipCodeService and most of the logic of this service is implemented on the interface's default
 * and static methods. It just calls the default method and gives to the main class for displaying
 */
public class ZipCodeServiceImpl implements ZipCodeService {
    private final static Logger logger = Logger.getLogger(ZipCodeServiceImpl.class.getName());

    /**
     * This method checks and accepts the filtered and parsed inputs from the ParseValidateUserInput and returns
     * the result to the main method
     * @param zipcodePairs
     * @return
     * @throws ZipCodeException
     * @throws IllegalAccessException
     */
    @Override
    public Set<ZipcodePair> restrictedZipRange(Set<ZipcodePair> zipcodePairs) throws ZipCodeException, IllegalAccessException {
        if (zipcodePairs==null||zipcodePairs.size()==0){
            logger.severe("Please check the given Zipcode pairs");
            throw new ZipCodeException("Invalid set of Zipcode pairs");
        }
        return getOverlappedValidZipcodePairs(new TreeSet<>(zipcodePairs));
    }
}
