package tests.junit;

import org.junit.Test;
import playground.essence.people.ManualEngineer;

import static org.junit.Assert.*;

public class ManualEngineerTest {

    @Test
    public void testConstructorAndGetters() {
        ManualEngineer eng = new ManualEngineer(28, 3);
        assertEquals(28, eng.getAge());
        assertEquals(3, eng.getExperience());
        assertEquals(6, eng.getSkill()); // experience * 2
    }

    @Test
    public void testSetters() {
        ManualEngineer eng = new ManualEngineer(40, 6);
        eng.setAge(45);
        eng.setExperience(8);
        eng.setSkill(20);
        assertEquals(45, eng.getAge());
        assertEquals(8, eng.getExperience());
        assertEquals(20, eng.getSkill());
    }
}
