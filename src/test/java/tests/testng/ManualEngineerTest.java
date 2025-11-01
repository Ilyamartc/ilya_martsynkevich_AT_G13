package tests.testng;

import org.testng.annotations.Test;
import playground.essence.people.ManualEngineer;

import static org.testng.Assert.*;

public class ManualEngineerTest {

    @Test
    public void testConstructorAndGetters() {
        ManualEngineer eng = new ManualEngineer(28, 3);
        assertEquals(eng.getAge(), 28);
        assertEquals(eng.getExperience(), 3);
        assertEquals(eng.getSkill(), 6);
    }

    @Test
    public void testSetters() {
        ManualEngineer eng = new ManualEngineer(40, 6);
        eng.setAge(45);
        eng.setExperience(8);
        eng.setSkill(20);
        assertEquals(eng.getAge(), 45);
        assertEquals(eng.getExperience(), 8);
        assertEquals(eng.getSkill(), 20);
    }
}
