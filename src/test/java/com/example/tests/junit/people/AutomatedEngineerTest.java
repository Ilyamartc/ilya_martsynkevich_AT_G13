// src/test/java/junit/people/AutomatedEngineerTest.java
package com.example.tests.junit.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.example.people.AutomatedEngineer;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AutomatedEngineerTest {
    private static final Logger logger = LogManager.getLogger(AutomatedEngineerTest.class);
    private final String name;
    private final int exp;
    private final String expectedType;

    public AutomatedEngineerTest(String name, int exp, String expectedType) {
        this.name = name;
        this.exp = exp;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Eve", 2, "Automated" },
                { "Dan", 6, "Automated" }
        });
    }

    @Test
    public void testAutomatedEngineerType() {
        logger.info("Testing AutomatedEngineer - Name: " + name + ", Experience: " + exp);
        AutomatedEngineer eng = new AutomatedEngineer(name, exp);
        logger.info("Created AutomatedEngineer, checking type");
        assertEquals(expectedType, eng.getType());
        logger.info("Test passed - Type matches: " + expectedType);
    }
}
