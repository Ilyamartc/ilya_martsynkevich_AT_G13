package classwork.day10;

import java.util.*;

public class ChildClass9 extends ChildClass7 {
    public ChildClass9(String name, int age, Sex sex) {
        super(name, age, sex);
    }

    public static double calculateAverageAge(List<ChildClass9> people) {
        return people.stream().mapToInt(p -> p.age).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Sex: " + sex + ")";
    }
}
