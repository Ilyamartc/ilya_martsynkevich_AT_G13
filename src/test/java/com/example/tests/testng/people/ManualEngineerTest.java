package com.example.tests.testng.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.people.ManualEngineer;

import static org.testng.Assert.assertEquals;

public class ManualEngineerTest {

    private static final Logger logger = LogManager.getLogger(ManualEngineerTest.class);

    @DataProvider(name = "manualEngineerData")
    public Object[][] createData() {
        return new Object[][] {
                { "Alice", 3, "Manual" },
                { "Bob", 5, "Manual" },
                { "Sarah", 8, "Manual" }
        };
    }

    @Test(dataProvider = "manualEngineerData", groups = { "people", "manual" })
    public void testManualEngineerType(String name, int exp, String expectedType) {
        logger.info("Testing manual engineer type with name: {}, experience: {}", name, exp);
        ManualEngineer engineer = new ManualEngineer(name, exp);
        logger.info("Created ManualEngineer instance");
        assertEquals(engineer.getType(), expectedType,
                "Engineer type should be " + expectedType);
        logger.info("Verified engineer type: {}", engineer.getType());
    }

    @Test(groups = { "people", "manual" })
    public void testManualEngineerName() {
        logger.info("Testing manual engineer name");
        ManualEngineer engineer = new ManualEngineer("TestUser", 4);
        logger.info("Created ManualEngineer with name: TestUser");
        assertEquals(engineer.getName(), "TestUser",
                "Engineer name should match");
        logger.info("Verified engineer name: {}", engineer.getName());
    }

    @Test(groups = { "people", "manual" })
    public void testManualEngineerExperience() {
        logger.info("Testing manual engineer experience");
        ManualEngineer engineer = new ManualEngineer("TestUser", 6);
        logger.info("Created ManualEngineer with experience: 6");
        assertEquals(engineer.getExperience(), 6,
                "Engineer experience should match");
        logger.info("Verified engineer experience: {}", engineer.getExperience());
    }
}
