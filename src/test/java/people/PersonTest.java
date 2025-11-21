package people;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class PersonTest {

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

    @Test
    public void testEquals() {
        Person p1 = new Person(20);
        Person p2 = new Person(20);
        Person p3 = new Person(25);

        assertEquals("Объекты с одинаковым age должны быть равны", p1, p2);
    }
    @Test
    public void testHashCode() {
        Person p1 = new Person(20);
        Person p2 = new Person(20);
    }
}
