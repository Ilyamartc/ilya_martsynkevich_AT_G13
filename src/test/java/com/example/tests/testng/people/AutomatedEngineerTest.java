package com.example.tests.testng.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.people.AutomatedEngineer;

import static org.testng.Assert.assertEquals;

public class AutomatedEngineerTest {

    private static final Logger logger = LogManager.getLogger(AutomatedEngineerTest.class);

    @DataProvider(name = "automatedEngineerData")
    public Object[][] createData() {
        return new Object[][] {
                {"Eve", 2, "Automated"},
                {"Dan", 6, "Automated"},
                {"Charlie", 4, "Automated"}
        };
    }

    @Test(dataProvider = "automatedEngineerData", groups = {"people", "automated"})
    public void testAutomatedEngineerType(String name, int exp, String expectedType) {
        logger.info("Testing automated engineer type with name: {}, experience: {}", name, exp);
        AutomatedEngineer engineer = new AutomatedEngineer(name, exp);
        logger.info("Created AutomatedEngineer instance");
        assertEquals(engineer.getType(), expectedType, 
            "Engineer type should be " + expectedType);
        logger.info("Verified engineer type: {}", engineer.getType());
    }

    @Test(groups = {"people", "automated"})
    public void testAutomatedEngineerName() {
        logger.info("Testing automated engineer name");
        AutomatedEngineer engineer = new AutomatedEngineer("TestUser", 5);
        logger.info("Created AutomatedEngineer with name: TestUser");
        assertEquals(engineer.getName(), "TestUser", 
            "Engineer name should match");
        logger.info("Verified engineer name: {}", engineer.getName());
    }

    @Test(groups = {"people", "automated"})
    public void testAutomatedEngineerExperience() {
        logger.info("Testing automated engineer experience");
        AutomatedEngineer engineer = new AutomatedEngineer("TestUser", 7);
        logger.info("Created AutomatedEngineer with experience: 7");
        assertEquals(engineer.getExperience(), 7, 
            "Engineer experience should match");
        logger.info("Verified engineer experience: {}", engineer.getExperience());
    }
}
