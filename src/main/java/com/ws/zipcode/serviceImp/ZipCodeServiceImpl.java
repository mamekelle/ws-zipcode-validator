package com.ws.zipcode.serviceImp;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ZipCodeService;
import java.util.*;

public class ZipCodeServiceImpl implements ZipCodeService {

    @Override
    public Set<ZipcodePair> restrictedZipRange(Set<ZipcodePair> zipcodePairs) throws ZipCodeException, IllegalAccessException {
        if (zipcodePairs==null||zipcodePairs.size()==0){
            throw new ZipCodeException("Invalid set of Zipcode pairs");
        }
        return getOverlappedValidZipcodePairs(new TreeSet<>(zipcodePairs));
    }
}
