package com.josemorenoesteban.lab.legolas.analysis;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnvironmentTest {
    public static final String LEGOLAS_TEST_PROPERTY = "LEGOLAS_TEST_PROPERTY";
    
    @Before
    public void setupSystemProperties() {
        System.setProperty(LEGOLAS_TEST_PROPERTY, LEGOLAS_TEST_PROPERTY);
    }

    @After
    public void cleanupSystemProperties() {
        System.clearProperty(LEGOLAS_TEST_PROPERTY);
    }

    @Test
    public void canGetDefaultValue() {
       final String OTHER_PROPERTY = "OTHER_PROPERTY";
       assertEquals(OTHER_PROPERTY, Environment.value.apply(OTHER_PROPERTY).orElse(OTHER_PROPERTY));
    }

    @Test
    public void canGetSystemPropertyValue() {
        assertEquals(LEGOLAS_TEST_PROPERTY, Environment.value.apply(LEGOLAS_TEST_PROPERTY).orElse("5"));
    }
}
