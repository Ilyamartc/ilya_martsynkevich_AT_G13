package com.example.tests.testng.people;

import com.example.testrail.TestRailCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

public class FailingStubTest {

    private static final Logger logger = LogManager.getLogger(FailingStubTest.class);

    @Test(groups = { "stub", "fail" })
    @TestRailCase(value = 158085, description = "C158085")
    public void testFailingStub() {
        logger.info("Running failing stub test");
        
        logger.error("This test is designed to fail");
        fail("This is an intentional failure for testing purposes");
    }
}