package com.example.tests.testng.people;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.people.AutomatedEngineer;

import static org.testng.Assert.assertEquals;

public class AutomatedEngineerTest {

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
        AutomatedEngineer engineer = new AutomatedEngineer(name, exp);
        assertEquals(engineer.getType(), expectedType, 
            "Engineer type should be " + expectedType);
    }

    @Test(groups = {"people", "automated"})
    public void testAutomatedEngineerName() {
        AutomatedEngineer engineer = new AutomatedEngineer("TestUser", 5);
        assertEquals(engineer.getName(), "TestUser", 
            "Engineer name should match");
    }

    @Test(groups = {"people", "automated"})
    public void testAutomatedEngineerExperience() {
        AutomatedEngineer engineer = new AutomatedEngineer("TestUser", 7);
        assertEquals(engineer.getExperience(), 7, 
            "Engineer experience should match");
    }
}
