package com.example.tests.testng.people;

import com.example.testrail.TestRailCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PassingStub3Test {

    private static final Logger logger = LogManager.getLogger(PassingStub3Test.class);

    @Test(groups = { "stub", "pass" })
    @TestRailCase(value = 158083, description = "C158083")
    public void testPassingStub3() {
        logger.info("Running third passing stub test");
        
        String expected = "Hello World";
        String actual = "Hello" + " " + "World";
        
        assertEquals(actual, expected, "String concatenation should work");
        logger.info("Third passing test completed successfully");
    }
}