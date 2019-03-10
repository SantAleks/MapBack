package com.cherentsov.mapback.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CityTest {
    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        String test = null;
        test.length();
    }
}