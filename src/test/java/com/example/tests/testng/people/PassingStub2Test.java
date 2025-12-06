package com.example.tests.testng.people;

import com.example.testrail.TestRailCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PassingStub2Test {

    private static final Logger logger = LogManager.getLogger(PassingStub2Test.class);

    @Test(groups = { "stub", "pass" })
    @TestRailCase(value = 158080, description = "C158080")
    public void testPassingStub2() {
        logger.info("Running second passing stub test");
        
        boolean condition = true;
        
        assertTrue(condition, "Condition should be true");
        logger.info("Second passing test completed successfully");
    }
}