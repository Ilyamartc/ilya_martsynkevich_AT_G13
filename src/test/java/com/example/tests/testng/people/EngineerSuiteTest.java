package com.example.tests.testng.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.example.people.AutomatedEngineer;
import com.example.people.ManualEngineer;

import static org.testng.Assert.*;

public class EngineerSuiteTest {

    private static final Logger logger = LogManager.getLogger(EngineerSuiteTest.class);

    @Test(groups = {"smoke", "people"})
    public void testAutomatedEngineerCreation() {
        logger.info("Testing automated engineer creation");
        AutomatedEngineer engineer = new AutomatedEngineer("John", 5);
        logger.info("Created AutomatedEngineer: John with 5 years experience");
        assertNotNull(engineer, "Engineer should not be null");
        logger.info("Verified engineer is not null");
        assertEquals(engineer.getType(), "Automated");
        logger.info("Verified engineer type: {}", engineer.getType());
    }

    @Test(groups = {"smoke", "people"})
    public void testManualEngineerCreation() {
        logger.info("Testing manual engineer creation");
        ManualEngineer engineer = new ManualEngineer("Jane", 3);
        logger.info("Created ManualEngineer: Jane with 3 years experience");
        assertNotNull(engineer, "Engineer should not be null");
        logger.info("Verified engineer is not null");
        assertEquals(engineer.getType(), "Manual");
        logger.info("Verified engineer type: {}", engineer.getType());
    }

    @Test(groups = {"regression", "people"})
    public void testDifferentEngineerTypes() {
        logger.info("Testing different engineer types");
        AutomatedEngineer automated = new AutomatedEngineer("Auto", 2);
        logger.info("Created AutomatedEngineer: Auto");
        ManualEngineer manual = new ManualEngineer("Manual", 2);
        logger.info("Created ManualEngineer: Manual");
        
        assertNotEquals(automated.getType(), manual.getType(), 
            "Different engineer types should not match");
        logger.info("Verified different types - Automated: {}, Manual: {}", automated.getType(), manual.getType());
    }

    @Test(groups = {"regression", "people"})
    public void testEngineerExperienceRange() {
        logger.info("Testing engineer experience range");
        AutomatedEngineer engineer = new AutomatedEngineer("Senior", 10);
        logger.info("Created AutomatedEngineer: Senior with 10 years experience");
        assertTrue(engineer.getExperience() >= 0, 
            "Experience should be non-negative");
        logger.info("Verified experience is non-negative: {}", engineer.getExperience());
        assertTrue(engineer.getExperience() <= 50, 
            "Experience should be reasonable");
        logger.info("Verified experience is within reasonable range: {}", engineer.getExperience());
    }
}
