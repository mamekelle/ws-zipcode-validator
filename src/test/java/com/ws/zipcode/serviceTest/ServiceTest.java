package com.ws.zipcode.serviceTest;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ZipCodeService;
import static org.junit.Assert.*;

import com.ws.zipcode.serviceImp.ZipCodeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import javax.inject.Inject;
import java.util.SortedSet;
import java.util.TreeSet;

public class ServiceTest {

    @Spy ZipCodeService zipCodeService = new ZipCodeServiceImpl();
    ZipcodePair zipcodePair;
    @Before
    public void setUp(){
        zipcodePair = new ZipcodePair.Builder().withMinRange(12345).withMaxRange(12346).build();
    }


    /**
     * Test to check by passing overlapped 2 object and it returns one by
     * taking the minimum of minRanges and the maximum of maxRanges
     * @throws ZipCodeException
     * @throws IllegalAccessException
     */
    @Test()
    public void restrictedZipRangeOverlapTest() throws ZipCodeException, IllegalAccessException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(zipcodePair);
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMaxRange(99989).withMinRange(12045).build());
        zipCodeService.getOverlappedValidZipcodePairs(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),1);
    }
    /**
     * Test to check by passing no overlapping between the 2 object and it returns 2 by
     * because nothing to merge
     * @throws ZipCodeException
     * @throws IllegalAccessException
     */
    @Test()
    public void restrictedZipRangeNoOverlapTest() throws ZipCodeException, IllegalAccessException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(zipcodePair);
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12348).withMaxRange(12370).build());
        zipCodeService.getOverlappedValidZipcodePairs(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),2);
    }

    @Test(expected = IllegalAccessException.class)
    public void restrictedZipRangeIllegalTest() throws IllegalAccessException, ZipCodeException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(null).withMaxRange(12370).build());
        //zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12348).withMaxRange(12370).build());
        zipCodeService.restrictedZipRange(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),1);
    }

}
