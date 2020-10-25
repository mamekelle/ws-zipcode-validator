package com.ws.zipcode.serviceImp;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.service.ZipCodeService;
import java.util.*;

public class ZipCodeServiceImpl implements ZipCodeService {

    @Override
    public Set<ZipcodePair> restrictedZipRange(Set<ZipcodePair> zipcodePairs) throws IllegalAccessException {
        if (zipcodePairs==null||zipcodePairs.size()==0){
            //Custom exception class to be added
            throw new IllegalArgumentException("Invalid set of Zipcode pairs");
        }
        return getOverlappedValidZipcodePairs(new TreeSet<>(zipcodePairs));
    }
}
