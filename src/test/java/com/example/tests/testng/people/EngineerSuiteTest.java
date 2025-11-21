package com.example.tests.testng.people;

import org.testng.annotations.Test;
import com.example.people.AutomatedEngineer;
import com.example.people.ManualEngineer;

import static org.testng.Assert.*;

public class EngineerSuiteTest {

    @Test(groups = {"smoke", "people"})
    public void testAutomatedEngineerCreation() {
        AutomatedEngineer engineer = new AutomatedEngineer("John", 5);
        assertNotNull(engineer, "Engineer should not be null");
        assertEquals(engineer.getType(), "Automated");
    }

    @Test(groups = {"smoke", "people"})
    public void testManualEngineerCreation() {
        ManualEngineer engineer = new ManualEngineer("Jane", 3);
        assertNotNull(engineer, "Engineer should not be null");
        assertEquals(engineer.getType(), "Manual");
    }

    @Test(groups = {"regression", "people"})
    public void testDifferentEngineerTypes() {
        AutomatedEngineer automated = new AutomatedEngineer("Auto", 2);
        ManualEngineer manual = new ManualEngineer("Manual", 2);
        
        assertNotEquals(automated.getType(), manual.getType(), 
            "Different engineer types should not match");
    }

    @Test(groups = {"regression", "people"})
    public void testEngineerExperienceRange() {
        AutomatedEngineer engineer = new AutomatedEngineer("Senior", 10);
        assertTrue(engineer.getExperience() >= 0, 
            "Experience should be non-negative");
        assertTrue(engineer.getExperience() <= 50, 
            "Experience should be reasonable");
    }
}
