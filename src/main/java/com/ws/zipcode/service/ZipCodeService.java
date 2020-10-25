package com.ws.zipcode.service;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.utilities.Utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

public interface ZipCodeService {
    Set<ZipcodePair> restrictedZipRange(Set<ZipcodePair> inputZipRange) throws ZipCodeException, IllegalAccessException;

    default SortedSet<ZipcodePair> getOverlappedValidZipcodePairs(SortedSet<ZipcodePair> zipcodePairSortedSet) throws ZipCodeException, IllegalAccessException {
        Iterator<ZipcodePair> zipcodePairIterator = zipcodePairSortedSet.iterator();
        ZipcodePair zipRangeHead = null;

        while (zipcodePairIterator.hasNext()) {
            ZipcodePair zipcodePair = zipcodePairIterator.next();

            Utils.validateZipRange(zipcodePair);
            if (zipRangeHead == null) {
                zipRangeHead = zipcodePair;
            } else if (isInside(zipRangeHead, zipcodePair)) {
                zipcodePairIterator.remove();
            } else if (noIntersection(zipRangeHead, zipcodePair)) {
                zipRangeHead = zipcodePair;
            } else {
                //Implementing the Java reflection API to update my ZipcodePair since it is an immutable class
                Field[] fields = ZipcodePair.class.getDeclaredFields();
                ZipcodePair zipcodePairUpdate = new ZipcodePair.Builder()
                        .withMaxRange(zipcodePair.getMaxRange())
                        .withMinRange(zipRangeHead.getMinRange()).build();
                for (Field field : fields) {
                    field.setAccessible(true); //need that if the fields are not
                    field.set(zipRangeHead, field.get(zipcodePairUpdate));
                }
                zipcodePairIterator.remove();
            }
        }
        return zipcodePairSortedSet;
    }

    static boolean isInside(ZipcodePair prevZipRange, ZipcodePair zipcodePair) {
        return (prevZipRange.getMaxRange() >= zipcodePair.getMaxRange());
    }

    static boolean noIntersection(ZipcodePair prevZipRange, ZipcodePair zipcodePair) {
        return (prevZipRange.getMaxRange() < zipcodePair.getMinRange());
    }

}
