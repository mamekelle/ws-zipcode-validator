package com.ws.zipcode.domainTest;

import com.ws.zipcode.domain.ZipcodePair;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Optional;

/**
 * Unit test for ZipcodePair model class
 */
public class ZipcodePairTest {
    /**
     * Method where we can test if the setup created the instance or not
     */
    @Test
    public void zipcodePairCreated(){
        ZipcodePair zipcodePair = new ZipcodePair.Builder().withMaxRange(67891).withMinRange(12345).build();
        assertNotNull(zipcodePair);
    }

    /**
     * Method where we can test if the setup created the instance is really working
     */
    @Test
    public void zipcodePairCreatedMax(){
        ZipcodePair zipcodePair = new ZipcodePair.Builder().withMaxRange(67891).withMinRange(12345).build();
        int temp=0;
        if(Optional.ofNullable(zipcodePair.getMaxRange()).isPresent())
            temp= Optional.ofNullable(zipcodePair.getMaxRange()).get();
        assertEquals(temp,67891);
    }

    /**
     * Method where we can test if the setup created the instance is working as expected
     */
    @Test
    public void zipcodePairCreatedMin(){
        ZipcodePair zipcodePair = new ZipcodePair.Builder().withMaxRange(67891).withMinRange(12345).build();
        int temp=0;
        if(Optional.ofNullable(zipcodePair.getMinRange()).isPresent())
            temp= Optional.ofNullable(zipcodePair.getMinRange()).get();
        assertNotEquals(temp,67891);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairCreatedOnlyMaxObject(){
        new ZipcodePair.Builder().withMaxRange(67891).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairCreatedOnlyMinObject(){
        new ZipcodePair.Builder().withMinRange(67891).build();
    }

    @Test(expected = AssertionError.class)
    public void zipcodePairCreatedNullObject(){
        assertEquals(null, new ZipcodePair.Builder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairCreatedNoObject(){
        new ZipcodePair.Builder().build();
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
