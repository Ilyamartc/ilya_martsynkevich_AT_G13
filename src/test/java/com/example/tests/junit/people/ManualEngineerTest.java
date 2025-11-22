// src/test/java/junit/people/ManualEngineerTest.java
package com.example.tests.junit.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.example.people.ManualEngineer;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ManualEngineerTest {
    private static final Logger logger = LogManager.getLogger(ManualEngineerTest.class);
    private final String name;
    private final int exp;
    private final String expectedType;

    public ManualEngineerTest(String name, int exp, String expectedType) {
        this.name = name;
        this.exp = exp;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Alice", 3, "Manual"},
                {"Bob", 5, "Manual"}
        });
    }

    @Test
    public void testManualEngineerType() {
        logger.info("Testing ManualEngineer - Name: " + name + ", Experience: " + exp);
        ManualEngineer eng = new ManualEngineer(name, exp);
        logger.info("Created ManualEngineer, checking type");
        assertEquals(expectedType, eng.getType());
        logger.info("Test passed - Type matches: " + expectedType);
    }
}
