package com.ws.zipcode.service;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.utilities.Utils;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Logger;

/**
 * This interface contains two static and one default methods for performing the main business logic of the project
 * I moved these codes to the interface's part is because they are independent from the service class and also to give more
 * space for service class if some other business logics comes and needed to be implemented in itself.
 */
public interface ZipCodeService {
    Logger logger = Logger.getLogger(ZipCodeService.class.getName());

    Set<ZipcodePair> restrictedZipRange(Set<ZipcodePair> inputZipRange) throws ZipCodeException, IllegalAccessException;

    default SortedSet<ZipcodePair> getOverlappedValidZipcodePairs(SortedSet<ZipcodePair> zipcodePairSortedSet) throws IllegalAccessException {
        logger.info("Creating the iterator to iterate on the sorted");
        Iterator<ZipcodePair> zipcodePairIterator = zipcodePairSortedSet.iterator();
        ZipcodePair zipRangeHead = null;

        logger.info("Iterating through the set and do apply the logic based on the overlapping or distinct pair of ranges");
        while (zipcodePairIterator.hasNext()) {
            ZipcodePair zipcodePair = zipcodePairIterator.next();

            Utils.validateZipRange(zipcodePair);
            if (zipRangeHead == null) {
                zipRangeHead = zipcodePair;
            } else if (isInside(zipRangeHead, zipcodePair)) {
                logger.info("The previous pair is inside of the current pair so it will be included in it");
                zipcodePairIterator.remove();
            } else if (noIntersection(zipRangeHead, zipcodePair)) {
                zipRangeHead = zipcodePair;
                logger.info("The previous pair has no any intersection with the current pair");
            } else {
                logger.info("The previous pair's maxRange is inside of the current pair");
                logger.info("Used the Java Reflection API to modify the immutable ZipCodePair class to make the overlap happen");
                Field[] fields = ZipcodePair.class.getDeclaredFields();
                ZipcodePair zipcodePairUpdate = new ZipcodePair.Builder()
                        .withMaxRange(zipcodePair.getMaxRange())
                        .withMinRange(zipRangeHead.getMinRange()).build();
                for (Field field : fields) {
                    field.setAccessible(true); //need that if the fields are not
                    field.set(zipRangeHead, field.get(zipcodePairUpdate));
                }
                logger.info("The previous is modified with the current's maxRange so we don't need no more the current object");
                zipcodePairIterator.remove();
            }
        }
        return zipcodePairSortedSet;
    }

    /**
     * This method accepts the previous and current ZipcodePair object and it returns true if the current object is inside of the
     * previous else it will return false
     * @param prevZipRange previous Zip code range to be checked
     * @param zipcodePair current Zip code range to be checked
     * @return return true if prevZipRange is inside of
     */
    static boolean isInside(ZipcodePair prevZipRange, ZipcodePair zipcodePair) {
        return (prevZipRange.getMaxRange() >= zipcodePair.getMaxRange());
    }

    /**
     * This method accepts the previous and current ZipcodePair object and it returns true if there is no intersection
     * between the two objects else it will return false
     * @param prevZipRange prevZipRange previous Zip code range to be checked
     * @param zipcodePair  current Zip code range to be checked
     * @return return true if no intersection else false
     */
    static boolean noIntersection(ZipcodePair prevZipRange, ZipcodePair zipcodePair) {
        return (prevZipRange.getMaxRange() < zipcodePair.getMinRange());
    }

}
