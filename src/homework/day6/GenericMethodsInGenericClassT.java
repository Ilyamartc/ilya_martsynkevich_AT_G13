package homework.day6;

public class GenericMethodsInGenericClassT<T> {
    private T value;

    public GenericMethodsInGenericClassT(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public <U> void genericMethodOneGenArg(U value) {
        System.out.println("I am an object of " + value.getClass().getSimpleName() + " class");
    }

    public <U, V> String genericMethodTwoGenArgs(U value1, V value2) {
        return "We are objects of " + value1.getClass().getSimpleName() + " class and " + value2.getClass().getSimpleName() + " class";
    }

    public <F> void genericMethodHalfGenArgs(F value, String str) {
        System.out.println("I got an object of " + value.getClass().getSimpleName() +
                " class and string with " + str.length() + " characters");
    }
}