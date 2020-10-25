package com.ws.zipcode.serviceTest;

import com.ws.zipcode.domain.ZipcodePair;
import com.ws.zipcode.exception.ZipCodeException;
import com.ws.zipcode.service.ZipCodeService;
import static org.junit.Assert.*;

import com.ws.zipcode.serviceImp.ZipCodeServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ZipcodeServiceTest {

    ZipCodeService zipCodeService;
    @Before
    public void setUp(){
        zipCodeService = new ZipCodeServiceImpl();
    }
    @Test
    public void created(){
        assertNotNull(zipCodeService);
    }

    /**
     * Test to check by passing overlapped 2 object and it returns one by
     * taking the minimum of minRanges and the maximum of maxRanges
     * @throws IllegalAccessException throws illegal exception
     */
    @Test()
    public void restrictedZipRangeOverlapTest() throws IllegalAccessException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12345).withMaxRange(12346).build());
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12045).withMaxRange(99989).build());
        zipCodeService.getOverlappedValidZipcodePairs(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),1);
    }
    /**
     * Test to check by passing no overlapping between the 2 object and it returns 2 by
     * because nothing to merge
     * @throws IllegalAccessException throws illegal exception
     */
    @Test()
    public void restrictedZipRangeNoOverlapTest() throws IllegalAccessException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12345).withMaxRange(12346).build());
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12348).withMaxRange(12370).build());
        zipCodeService.getOverlappedValidZipcodePairs(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),2);
    }

    @Test()
    public void restrictedZipRangeValidated() throws ZipCodeException, IllegalAccessException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12345).withMaxRange(12346).build());
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12348).withMaxRange(12370).build());
        Set<ZipcodePair> result=zipCodeService.restrictedZipRange(zipcodePairSortedSet);
        assertEquals(result.size(),2);
    }

    @Test(expected = ZipCodeException.class)
    public void restrictedZipRangeIllegalValue() throws ZipCodeException, IllegalAccessException {
        zipCodeService.restrictedZipRange(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void restrictedZipRangeIllegalTest() throws IllegalAccessException, ZipCodeException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(null).withMaxRange(12370).build());
        zipCodeService.restrictedZipRange(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairMinGreaterMax() throws IllegalAccessException, ZipCodeException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(89898).withMaxRange(12370).build());
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(89898).withMaxRange(null).build());
        zipCodeService.restrictedZipRange(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),1);
    }

    @Test()
    public void zipcodePairMerge() throws IllegalAccessException {
        SortedSet<ZipcodePair> zipcodePairSortedSet = new TreeSet<>();
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12345).withMaxRange(99989).build());
        zipcodePairSortedSet.add(new ZipcodePair.Builder().withMinRange(12045).withMaxRange(12346).build());
        zipCodeService.getOverlappedValidZipcodePairs(zipcodePairSortedSet);
        assertEquals(zipcodePairSortedSet.size(),1);
    }

}
