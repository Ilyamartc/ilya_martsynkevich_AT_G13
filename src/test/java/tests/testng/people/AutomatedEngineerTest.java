package tests.testng.people;

import org.testng.annotations.Test;
import playground.essence.people.AutomatedEngineer;

import static org.testng.Assert.*;

public class AutomatedEngineerTest {

    @Test
    public void testConstructorAndGetters() {
        AutomatedEngineer eng = new AutomatedEngineer(25, 4);
        assertEquals(eng.getAge(), 25);
        assertEquals(eng.getExperience(), 4);
        assertEquals(eng.getSkill(), 12);
    }

    @Test
    public void testSetters() {
        AutomatedEngineer eng = new AutomatedEngineer(30, 5);
        eng.setAge(35);
        eng.setExperience(10);
        eng.setSkill(50);
        assertEquals(eng.getAge(), 35);
        assertEquals(eng.getExperience(), 10);
        assertEquals(eng.getSkill(), 50);
    }
}
