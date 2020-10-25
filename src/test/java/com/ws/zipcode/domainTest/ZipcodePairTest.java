package com.ws.zipcode.domainTest;

import com.ws.zipcode.domain.ZipcodePair;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class ZipcodePairTest {

    ZipcodePair zipcodePair;

    @Before
    public void setup(){
        zipcodePair = new ZipcodePair.Builder().withMaxRange(67891).withMinRange(12345).build();
    }

    @Test
    public void zipcodePairCreated(){
       assertNotNull(zipcodePair);
    }

    @Test
    public void zipcodePairCreatedMax(){
        assertTrue(zipcodePair.getMaxRange()==67891);
    }

    @Test
    public void zipcodePairCreatedMin(){
        assertFalse(zipcodePair.getMaxRange()!=67891);
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairNullMax(){
        new ZipcodePair.Builder().withMaxRange(null).withMinRange(12345).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void zipcodePairNullMin(){
        new ZipcodePair.Builder().withMaxRange(12345).withMinRange(null).build();
    }
}
