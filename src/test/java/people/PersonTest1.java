package people;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest1 {

    @Test
    public void testGetSetAge() {
        Person person = new Person();
        person.setAge(25);
        assertEquals("Возраст должен совпадать после setAge()", 25, person.getAge());
    }

    @Test
    public void testConstructor() {
        Person person = new Person(30);
        assertEquals("Возраст должен быть установлен через конструктор", 30, person.getAge());
    }
}
