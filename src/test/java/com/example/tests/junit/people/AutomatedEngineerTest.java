// src/test/java/junit/people/AutomatedEngineerTest.java
package com.example.tests.junit.people;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.example.people.AutomatedEngineer;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AutomatedEngineerTest {
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
                {"Eve", 2, "Automated"},
                {"Dan", 6, "Automated"}
        });
    }

    @Test
    public void testAutomatedEngineerType() {
        AutomatedEngineer eng = new AutomatedEngineer(name, exp);
        assertEquals(expectedType, eng.getType());
    }
}
