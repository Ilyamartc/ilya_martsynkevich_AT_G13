package classwork.day10;

import java.util.*;

public class ChildClass7 {
    public String name;
    public int age;
    public Sex sex;

    public ChildClass7(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public enum Sex {
        MAN, WOMEN
    }

    @Override
    public String toString() {
        return name + " (" + age + ", " + sex + ")";
    }
}