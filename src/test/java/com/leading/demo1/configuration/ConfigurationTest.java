package com.leading.demo1.configuration;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    private Configuration config;
    @Before
    public void setUp(){
        config = new ConfigurationCloud();
    }

    @Test
    public void testGetAccessThreshold() {
        assertEquals(Integer.valueOf("2"), config.getAccessThreshold());
    }

    @Test
    public void testGetIntervalThreshold() {
        assertEquals(Long.valueOf("10000"), config.getIntervalThreshold());
    }
}
