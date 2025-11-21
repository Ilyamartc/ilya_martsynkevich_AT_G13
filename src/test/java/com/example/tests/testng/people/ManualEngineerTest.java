package com.example.tests.testng.people;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.example.people.ManualEngineer;

import static org.testng.Assert.assertEquals;

public class ManualEngineerTest {

    @DataProvider(name = "manualEngineerData")
    public Object[][] createData() {
        return new Object[][] {
                {"Alice", 3, "Manual"},
                {"Bob", 5, "Manual"},
                {"Sarah", 8, "Manual"}
        };
    }

    @Test(dataProvider = "manualEngineerData", groups = {"people", "manual"})
    public void testManualEngineerType(String name, int exp, String expectedType) {
        ManualEngineer engineer = new ManualEngineer(name, exp);
        assertEquals(engineer.getType(), expectedType, 
            "Engineer type should be " + expectedType);
    }

    @Test(groups = {"people", "manual"})
    public void testManualEngineerName() {
        ManualEngineer engineer = new ManualEngineer("TestUser", 4);
        assertEquals(engineer.getName(), "TestUser", 
            "Engineer name should match");
    }

    @Test(groups = {"people", "manual"})
    public void testManualEngineerExperience() {
        ManualEngineer engineer = new ManualEngineer("TestUser", 6);
        assertEquals(engineer.getExperience(), 6, 
            "Engineer experience should match");
    }
}
