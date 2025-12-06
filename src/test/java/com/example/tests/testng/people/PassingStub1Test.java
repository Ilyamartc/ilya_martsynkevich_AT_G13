package com.example.tests.testng.people;

import com.example.testrail.TestRailCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PassingStub1Test {

    private static final Logger logger = LogManager.getLogger(PassingStub1Test.class);

    @Test(groups = { "stub", "pass" })
    @TestRailCase(value = 158074, description = "C158074")
    public void testPassingStub1() {
        logger.info("Running first passing stub test");
        
        int expectedValue = 10;
        int actualValue = 5 + 5;
        
        assertEquals(actualValue, expectedValue, "Simple math should work correctly");
        logger.info("First passing test completed successfully");
    }
}