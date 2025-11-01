package tests.junit;

import org.junit.Test;
import playground.essence.people.AutomatedEngineer;

import static org.junit.Assert.*;

public class AutomatedEngineerTest {

    @Test
    public void testConstructorAndGetters() {
        AutomatedEngineer eng = new AutomatedEngineer(25, 4);
        assertEquals(25, eng.getAge());
        assertEquals(4, eng.getExperience());
        assertEquals(12, eng.getSkill()); // experience * 3
    }

    @Test
    public void testSetters() {
        AutomatedEngineer eng = new AutomatedEngineer(30, 5);
        eng.setAge(35);
        eng.setExperience(10);
        eng.setSkill(50);
        assertEquals(35, eng.getAge());
        assertEquals(10, eng.getExperience());
        assertEquals(50, eng.getSkill());
    }
}
