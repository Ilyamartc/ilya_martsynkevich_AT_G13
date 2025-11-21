package people;

import java.util.Objects;

public class Person {
    private int age;

    public Person() {
    }

    public Person(int age) {
        this.age = age;
    }

    // Getter
    public int getAge() {
        return age;
    }

    // Setter
    public void setAge(int age) {
        this.age = age;
    }

    // equals / hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    // toString
    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
