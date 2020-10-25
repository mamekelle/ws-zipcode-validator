package com.ws.zipcode.domainTest;

import com.ws.zipcode.domain.ZipcodePair;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for ZipcodePair model class
 */
public class ZipcodePairTest {

    ZipcodePair zipcodePair;

    /**
     * SetUp method always get executed before each test
     */
    @Before
    public void setup(){
        zipcodePair = new ZipcodePair.Builder().withMaxRange(67891).withMinRange(12345).build();
    }

    /**
     * Method where we can test if the setup created the instance or not
     */
    @Test
    public void zipcodePairCreated(){
       assertNotNull(zipcodePair);
    }

    /**
     * Method where we can test if the setup created the instance is really working
     */
    @Test
    public void zipcodePairCreatedMax(){
        assertTrue(zipcodePair.getMaxRange()==67891);
    }

    /**
     * Method where we can test if the setup created the instance is working as expected
     */
    @Test
    public void zipcodePairCreatedMin(){
        assertFalse(zipcodePair.getMaxRange()!=67891);
    }

    /**
     * Method where we can test if the the Exception is working correctly
     */

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairNullMax(){
        new ZipcodePair.Builder().withMaxRange(null).withMinRange(12345).build();
    }
    /**
     * Method where we can test if the the Exception is working correctly
     */
    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairNullMin(){
        new ZipcodePair.Builder().withMaxRange(12345).withMinRange(null).build();
    }
}
